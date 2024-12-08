package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;

import java.util.List;

public interface BankAccountRepository {

    List<BankAccount> findAllBankAccount();
    BankAccount createBankAccount(BankAccount createBankAccount);
    BankAccount updateBankAccount(BankAccount updateBankAccount, Integer id);
}
