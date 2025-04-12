package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.command.ImportBankTransactionsCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface ImportBankTransactionsUseCase {

    List<Transaction> handle(ImportBankTransactionsCommand command) throws IOException;
}
