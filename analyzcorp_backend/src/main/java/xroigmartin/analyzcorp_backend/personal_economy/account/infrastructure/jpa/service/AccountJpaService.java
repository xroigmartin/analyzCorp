package xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.repository.AccountRepository;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.repository.AccountJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.utils.AccountJpaUtils;

import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public Optional<Account> findAccountById(Long id) {
        var accountJpa = this.accountJpaRepository.findById(id);

        return accountJpa.map(AccountJpaUtils::convertAccountJpaToAccount);
    }

    @Override
    @Transactional
    public Account createAccount(Account newBankAccount) {
        var newBankAccountJpa = AccountJpa.builder()
                .bankName(newBankAccount.bankName())
                .alias(newBankAccount.alias())
                .iban(newBankAccount.iban())
                .createdBy(newBankAccount.createdBy())
                .createdAt(newBankAccount.createdAt())
                .updatedAt(newBankAccount.updatedAt())
                .updatedBy(newBankAccount.updatedBy())
                .build();

        var bankAccount = this.accountJpaRepository.save(newBankAccountJpa);

        return AccountJpaUtils.convertAccountJpaToAccount(bankAccount);
    }

    @Override
    @Transactional
    public Account updateAccount(Account updateAccount) {

        var bankAccountForUpdate = AccountJpa.builder()
                .id(updateAccount.id())
                .bankName(updateAccount.bankName())
                .iban(updateAccount.iban())
                .alias(updateAccount.alias())
                .createdAt(updateAccount.createdAt())
                .createdBy(updateAccount.createdBy())
                .updatedAt(updateAccount.updatedAt())
                .updatedBy(updateAccount.updatedBy())
                .build();

        var bankAccountUpdated = this.accountJpaRepository.save(bankAccountForUpdate);

        return AccountJpaUtils.convertAccountJpaToAccount(bankAccountUpdated);
    }
}
