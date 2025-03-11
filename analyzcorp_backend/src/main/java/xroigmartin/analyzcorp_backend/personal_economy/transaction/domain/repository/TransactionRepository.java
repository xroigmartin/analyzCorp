package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    Transaction createTransaction(Transaction transaction);

    void createListOfTransaction(List<Transaction> transactions);

    List<Transaction> findTransactionsByAccountId(Long accountId);

    Transaction getTransactionById(Long transactionId);
    Transaction updateTransaction(Transaction transaction);
}
