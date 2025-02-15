package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.ImportCuaderno43;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public class CaixaBankCuaderno43ExtractTransactionServiceImpl implements ImportCuaderno43 {

    @Override
    public void importCuaderno43(Account account, MultipartFile file, List<Transaction> transactions) throws IOException {
        try(Workbook workbook = new HSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                if(row.getRowNum() < 4){
                    continue;
                }

                var transaction = parseRowCuaderno43(account.id(), row);

                if(transaction != null) {
                    transactions.add(transaction);
                }
            }
        }
    }

    private Transaction parseRowCuaderno43(Long accountId, Row row) {

        try{
            String currency = row.getCell(3).getStringCellValue();
            Cell income = row.getCell(6);
            Cell expense = row.getCell(7);
            String description = row.getCell(14).getStringCellValue();

            OffsetDateTime date = parseDate(row.getCell(4).getStringCellValue(), description);
            description = parseDescription(description);
            var amount = parseAmount(income, expense);

            TransactionType type = amount.compareTo(BigDecimal.ZERO) >= 0 ? TransactionType.INCOME : TransactionType.EXPENSE;

            return Transaction.builder()
                    .amount(amount)
                    .accountId(accountId)
                    .currency(currency)
                    .date(date)
                    .description(description)
                    .type(type)
                    .createdAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                    .createdBy("SYSTEM")
                    .updatedAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                    .updatedBy("SYSTEM")
                    .build();
        }
        catch(Exception e){
            System.err.println("Error process row: " + row.getRowNum() + " : " + e.getMessage());
            return null;
        }
    }

    private OffsetDateTime parseDate(String date, String description) {
        final Pattern pattern = Pattern.compile("Fecha de operación:\\s*(\\d{2}-\\d{2}-\\d{4})\\s*(.+)");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        final Matcher matcher = pattern.matcher(description);

        if(matcher.find()) {
            LocalDate localDate = LocalDate.parse(matcher.group(1), formatter);
            return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
        }

        final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter2);
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);

    }

    private BigDecimal parseAmount(Cell income, Cell expense) {
        BigDecimal incomeAmount = getCellValueAsBigDecimal(income);
        BigDecimal expenseAmount = getCellValueAsBigDecimal(expense);

        if (incomeAmount.compareTo(BigDecimal.ZERO) > 0) {
            return incomeAmount;
        } else if (expenseAmount.compareTo(BigDecimal.ZERO) > 0) {
            return expenseAmount.negate();
        }

        return BigDecimal.ZERO;
    }

    private BigDecimal getCellValueAsBigDecimal(Cell cell) {
        if(cell == null) {
            return BigDecimal.ZERO;
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return BigDecimal.valueOf(cell.getNumericCellValue());
            case STRING:
                try {
                    return new BigDecimal(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    return BigDecimal.ZERO;
                }
            default:
                return BigDecimal.ZERO;
        }
    }

    private String parseDescription(String description) {
        final Pattern pattern = Pattern.compile("Fecha de operación:\\s*(\\d{2}-\\d{2}-\\d{4})\\s*(.+)");

        final Matcher matcher = pattern.matcher(description);

        if(matcher.find()){
            return matcher.group(2).trim();
        }

        return description;
    }
}
