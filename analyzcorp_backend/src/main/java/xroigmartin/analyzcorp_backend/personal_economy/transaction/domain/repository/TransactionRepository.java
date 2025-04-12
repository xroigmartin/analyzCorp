package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
    void createListOfTransaction(List<Transaction> transactions);
    List<Transaction> findTransactionsByAccountId(Long accountId);
    Optional<Transaction> findTransactionById(Long transactionId);
}
