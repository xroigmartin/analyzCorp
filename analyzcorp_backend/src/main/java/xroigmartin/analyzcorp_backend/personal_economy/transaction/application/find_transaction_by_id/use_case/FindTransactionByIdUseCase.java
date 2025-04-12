package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.command.FindTransactionByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public interface FindTransactionByIdUseCase {

    Transaction handle(FindTransactionByIdCommand command);
}
