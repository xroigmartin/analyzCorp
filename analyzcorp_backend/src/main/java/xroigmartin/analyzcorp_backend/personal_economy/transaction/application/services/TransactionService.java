package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionDTO createTransaction);
    void importFile(Long accountId, MultipartFile file, FileImportType fileImportType) throws IOException;
    List<Transaction> findTransactionsByAccountId(Long accountId);

}
