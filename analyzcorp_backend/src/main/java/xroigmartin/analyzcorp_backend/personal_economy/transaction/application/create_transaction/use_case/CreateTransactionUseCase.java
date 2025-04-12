package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.command.CreateTransactionCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public interface CreateTransactionUseCase {

    Transaction handle(CreateTransactionCommand command);
}
