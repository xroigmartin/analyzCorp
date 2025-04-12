package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.service.caixa_bank;

import lombok.AllArgsConstructor;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case.FindCategoryByKeywordUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.command.ImportBankTransactionsCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.use_case.ImportBankTransactionsUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public final class ImportCaixaBankTransactionsService implements ImportBankTransactionsUseCase {

    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private final FindCategoryByKeywordUseCase findCategoryByKeywordUseCase;

    @Override
    public List<Transaction> handle(ImportBankTransactionsCommand command) throws IOException {
        return switch (command.fileImportType()) {
            case CUADERNO_43 -> new ImportCaixaBankCuaderno43ExtractTransactionService(findAllCategoriesUseCase, findCategoryByKeywordUseCase)
                    .handle(command);
            case EXTRACTO_MENSUAL -> new ImportCaixaBankMonthlyExtractTransactionsService(findAllCategoriesUseCase, findCategoryByKeywordUseCase)
                        .handle(command);
        };
    }
}
