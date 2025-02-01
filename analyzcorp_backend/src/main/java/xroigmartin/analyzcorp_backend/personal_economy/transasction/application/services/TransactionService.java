package xroigmartin.analyzcorp_backend.personal_economy.transasction.application.services;

import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.interfaces.dto.transaction.CreateTransactionDTO;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionDTO createTransaction);

}
