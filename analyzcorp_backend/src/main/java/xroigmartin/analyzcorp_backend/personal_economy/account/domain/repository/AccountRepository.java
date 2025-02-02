package xroigmartin.analyzcorp_backend.personal_economy.account.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<Account> findAllAccount();
    Optional<Account> findAccountById(Long id);
    Account createAccount(Account createAccount);
    Account updateAccount(Account updateAccount);
}
