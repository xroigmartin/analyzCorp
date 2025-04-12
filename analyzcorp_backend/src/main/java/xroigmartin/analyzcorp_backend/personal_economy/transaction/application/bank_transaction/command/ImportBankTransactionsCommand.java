package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.command;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.util.List;

public record ImportBankTransactionsCommand(
        Account account,
        MultipartFile file,
        List<Transaction> transactions,
        FileImportType fileImportType,
        String createdBy
) {
    public static ImportBankTransactionsCommand create(Account account, MultipartFile file, List<Transaction> transactions,
                                                       FileImportType fileImportType, String createdBy) {
        return new ImportBankTransactionsCommand(account, file, transactions, fileImportType, createdBy);
    }
}
