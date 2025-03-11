package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.UpdateTransactionDTO;

import java.io.IOException;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionDTO createTransaction);
    void importFile(Long accountId, MultipartFile file, FileImportType fileImportType) throws IOException;
    List<Transaction> findTransactionsByAccountId(Long accountId);

    Transaction updateTransaction(Long id, UpdateTransactionDTO updateTransaction);
    Transaction getTransactionById(Long id);
}
