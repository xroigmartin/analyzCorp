package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case.FindCategoryByKeywordUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.BankTransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public final class CaixaBankTransactionServiceImpl implements BankTransactionService {

    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private final FindCategoryByKeywordUseCase findCategoryByKeywordUseCase;

    @Override
    public void importFile(Account account, MultipartFile file, List<Transaction> transactions, FileImportType fileImportType) throws IOException {

        switch (fileImportType) {
            case CUADERNO_43 -> new CaixaBankCuaderno43ExtractTransactionServiceImpl(findAllCategoriesUseCase, findCategoryByKeywordUseCase)
                    .importCuaderno43(account, file, transactions);
            case EXTRACTO_MENSUAL -> new CaixaBankMonthlyExtractTransactionServiceImpl(findAllCategoriesUseCase, findCategoryByKeywordUseCase)
                    .importMonthlyExtract(account, file, transactions);
        }
    }

}
