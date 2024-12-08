package xroigmartin.analyzcorp_backend.personal_economy.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.domain.model.BankAccount;

import java.util.List;

public interface ListBankAccountRepository {

    List<BankAccount> findAllBankAccount();
}
