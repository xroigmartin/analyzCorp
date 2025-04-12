package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.service.caixa_bank;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case.FindCategoryByKeywordUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.command.ImportBankTransactionsCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.service.ImportBankTransactionsBaseService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.use_case.ImportMonthlyExtractUseCase;
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

public class ImportCaixaBankMonthlyExtractTransactionsService extends ImportBankTransactionsBaseService implements ImportMonthlyExtractUseCase {

    public ImportCaixaBankMonthlyExtractTransactionsService(FindAllCategoriesUseCase findAllCategoriesUseCase, FindCategoryByKeywordUseCase findCategoryByKeywordUseCase) {
        super(findAllCategoriesUseCase, findCategoryByKeywordUseCase);
    }

    @Override
    public List<Transaction> handle(ImportBankTransactionsCommand command) throws IOException {
        try(Workbook workbook = new HSSFWorkbook(command.file().getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                if(row.getRowNum() < 3){
                    continue;
                }

                var transaction = parseRowMonthlyExtract(command.account(), row, Currency.create("EUR", null), command.createdBy());

                if(transaction != null) {
                    command.transactions().add(transaction);
                }
            }
            return command.transactions();
        }
    }

    private Transaction parseRowMonthlyExtract(Account account, Row row, Currency currency, String createdBy) {

        try{
            String description = row.getCell(3).getStringCellValue() + " " + row.getCell(2).getStringCellValue();

            OffsetDateTime date = parseDate(row.getCell(1).getLocalDateTimeCellValue(), description);
            description = parseDescription(description);

            BigDecimal amount = getCellValueAsBigDecimal(row.getCell(4));
            TransactionType type = amount.compareTo(BigDecimal.ZERO) >= 0 ? TransactionType.INCOME : TransactionType.EXPENSE;

            Category category = getCategoryByKeyword(description);

            return Transaction.create(null, new AmountValueObject(amount), currency, date, type, description,
                    category, account, createdBy, createdBy);
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
