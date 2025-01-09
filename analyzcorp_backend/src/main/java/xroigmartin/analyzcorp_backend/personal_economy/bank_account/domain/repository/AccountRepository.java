package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAllAccount();
    Account createAccount(Account createAccount);
    Account updateAccount(Account updateAccount, long id);
}
