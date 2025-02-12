package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionDTO createTransaction);

}
