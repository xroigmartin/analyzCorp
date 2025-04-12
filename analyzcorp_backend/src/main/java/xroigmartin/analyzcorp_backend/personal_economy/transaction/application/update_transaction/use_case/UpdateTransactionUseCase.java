package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.command.UpdateTransactionCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public interface UpdateTransactionUseCase {
    Transaction handle(UpdateTransactionCommand command);
}
