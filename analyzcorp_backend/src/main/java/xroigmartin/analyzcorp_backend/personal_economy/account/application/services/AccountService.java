package xroigmartin.analyzcorp_backend.personal_economy.account.application.services;

import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    @Deprecated
    List<Account> findAllAccount();
    @Deprecated
    Account findAccountById(long id);
    @Deprecated
    Account createAccount(Account createAccount);
    @Deprecated
    Account updateAccount(Account updateAccount);

    Optional<Account> findAccountById(Long id);
}
