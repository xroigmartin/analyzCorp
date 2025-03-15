package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.CategoryService;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.ImportMonthlyExtract;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.BankTransactionBaseService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaixaBankMonthlyExtractTransactionServiceImpl extends BankTransactionBaseService implements ImportMonthlyExtract {

    public CaixaBankMonthlyExtractTransactionServiceImpl(CategoryService categoryService) {
        super(categoryService);
    }

    @Override
    public void importMonthlyExtract(Account account, MultipartFile file, List<Transaction> transactions) throws IOException {
        try(Workbook workbook = new HSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                if(row.getRowNum() < 3){
                    continue;
                }

                var transaction = parseRowMonthlyExtract(account.id(), row, Currency.builder().code("EUR").build());

                if(transaction != null) {
                    transactions.add(transaction);
                }
            }
        }
    }

    private Transaction parseRowMonthlyExtract(Long accountId, Row row, Currency currency) {

        try{
            String description = row.getCell(3).getStringCellValue() + " " + row.getCell(2).getStringCellValue();

            OffsetDateTime date = parseDate(row.getCell(1).getLocalDateTimeCellValue(), description);
            description = parseDescription(description);

            BigDecimal amount = getCellValueAsBigDecimal(row.getCell(4));
            TransactionType type = amount.compareTo(BigDecimal.ZERO) >= 0 ? TransactionType.INCOME : TransactionType.EXPENSE;

            Category category = getCategoryByDescription(description);

            return Transaction.builder()
                    .amount(amount)
                    .accountId(accountId)
                    .currency(currency.code())
                    .date(date)
                    .description(description)
                    .category(category)
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

    private OffsetDateTime parseDate(LocalDateTime localDateTimeCellValue, String description) {
        final Pattern pattern = Pattern.compile("Fecha de operación:\\s*(\\d{2}-\\d{2}-\\d{4})\\s*(.+)");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        final Matcher matcher = pattern.matcher(description);

        if(matcher.find()) {
            LocalDate localDate = LocalDate.parse(matcher.group(1), formatter);
            return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
        }

        return localDateTimeCellValue.atOffset(ZoneOffset.UTC);
    }
}
