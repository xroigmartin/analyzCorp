package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionDTO createTransaction);

}
