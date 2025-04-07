package xroigmartin.analyzcorp_backend.personal_economy.account.application.create_account.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.account.application.create_account.command.CreateAccountCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

public interface CreateAccountUseCase {

    Account handle(CreateAccountCommand command);
}
