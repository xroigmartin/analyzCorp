package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Transaction;

public interface TransactionRepository {

    Transaction createTransaction(Transaction transaction);
}
