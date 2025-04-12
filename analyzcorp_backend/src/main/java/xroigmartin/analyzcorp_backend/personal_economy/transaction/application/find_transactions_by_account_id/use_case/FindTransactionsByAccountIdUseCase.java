package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.command.FindTransactionsByAccountIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.util.List;

public interface FindTransactionsByAccountIdUseCase {
    List<Transaction> handle(FindTransactionsByAccountIdCommand command);
}
