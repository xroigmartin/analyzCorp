package xroigmartin.analyzcorp_backend.personal_economy.account.application.services;

import java.util.List;

import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

public interface AccountService {

    List<Account> findAllAccount();
    Account findAccountById(long id);
    Account createAccount(Account createAccount);
    Account updateAccount(Account updateAccount);
}
