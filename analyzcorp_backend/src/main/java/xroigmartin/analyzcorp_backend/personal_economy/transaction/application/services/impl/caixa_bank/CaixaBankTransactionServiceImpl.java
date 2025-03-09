package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.BankTransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.io.IOException;
import java.util.List;

public final class CaixaBankTransactionServiceImpl implements BankTransactionService {

    @Override
    public void importFile(Account account, MultipartFile file, List<Transaction> transactions, FileImportType fileImportType, Category category) throws IOException {

        switch (fileImportType) {
            case CUADERNO_43 -> new CaixaBankCuaderno43ExtractTransactionServiceImpl()
                    .importCuaderno43(account, file, transactions, category);
            case EXTRACTO_MENSUAL -> new CaixaBankMonthlyExtractTransactionServiceImpl()
                    .importMonthlyExtract(account, file, transactions, category);
        }
    }
}
