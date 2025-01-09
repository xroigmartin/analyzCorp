package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.repository.AccountRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.AccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.repository.AccountJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.utils.AccountJpaUtils;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AccountJpaService implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAllAccount() {
        var bankAccounts = this.accountJpaRepository.findAll();
        return bankAccounts.stream().map(AccountJpaUtils::convertAccountJpaToAccount).toList();
    }

    @Override
    @Transactional
    public Account createAccount(Account newBankAccount) {
        var newBankAccountJpa = AccountJpa.builder()
                .bankName(newBankAccount.bankName())
                .alias(newBankAccount.alias())
                .iban(newBankAccount.iban())
                .build();

        var bankAccount = this.accountJpaRepository.save(newBankAccountJpa);

        return AccountJpaUtils.convertAccountJpaToAccount(bankAccount);
    }

    @Override
    @Transactional
    public Account updateAccount(Account updateAccount, long id) {
        var oldBankAccount = this.accountJpaRepository.getReferenceById(id);

        var bankAccountForUpdate = AccountJpa.builder()
                .id(oldBankAccount.getId())
                .bankName(updateAccount.bankName())
                .iban(updateAccount.iban())
                .alias(updateAccount.alias())
                .build();

        var bankAccountUpdated = this.accountJpaRepository.save(bankAccountForUpdate);

        return AccountJpaUtils.convertAccountJpaToAccount(bankAccountUpdated);
    }
}
