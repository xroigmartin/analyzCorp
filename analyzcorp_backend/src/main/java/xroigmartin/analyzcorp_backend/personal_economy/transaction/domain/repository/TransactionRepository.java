package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository;

import java.util.List;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public interface TransactionRepository {

    Transaction createTransaction(Transaction transaction);

    void createListOfTransaction(List<Transaction> transactions);

    List<Transaction> findTransactionsByAccountId(Long accountId);
}
