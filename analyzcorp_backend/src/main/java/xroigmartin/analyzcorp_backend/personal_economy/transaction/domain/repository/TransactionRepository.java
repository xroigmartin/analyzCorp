package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public interface TransactionRepository {

    Transaction createTransaction(Transaction transaction);
}
