package xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

public interface FindAccountByIdUseCase {

    Account handle(FindAccountByIdCommand command);
}
