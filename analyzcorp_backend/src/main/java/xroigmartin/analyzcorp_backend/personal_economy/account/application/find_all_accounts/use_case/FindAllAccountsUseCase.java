package xroigmartin.analyzcorp_backend.personal_economy.account.application.find_all_accounts.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

import java.util.List;

public interface FindAllAccountsUseCase {

    List<Account> handle();
}
