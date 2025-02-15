package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public interface BankTransactionService {

    void importFile(Account account, MultipartFile file, List<Transaction> transactions, FileImportType fileImportType) throws IOException;


}
