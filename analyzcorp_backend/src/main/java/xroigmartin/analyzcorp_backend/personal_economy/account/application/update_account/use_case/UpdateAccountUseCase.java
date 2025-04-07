package xroigmartin.analyzcorp_backend.personal_economy.account.application.update_account.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.account.application.update_account.command.UpdateAccountCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

public interface UpdateAccountUseCase {

    Account handle(UpdateAccountCommand command);
}
