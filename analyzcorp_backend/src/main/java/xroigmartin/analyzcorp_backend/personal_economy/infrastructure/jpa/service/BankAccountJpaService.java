package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.domain.repository.BankAccountRepository;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain.BankAccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.repository.BankAccountJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.utils.BankAccountJpaUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountJpaService implements BankAccountRepository {

    private final BankAccountJpaRepository bankAccountJpaRepository;

    @Override
    public List<BankAccount> findAllBankAccount() {
        var bankAccounts = this.bankAccountJpaRepository.findAll();
        return bankAccounts.stream().map(BankAccountJpaUtils::convertBankAccountJpaToBankAccount).toList();
    }

    @Override
    public BankAccount createBankAccount(BankAccount newBankAccount) {
        var newBankAccountJpa = BankAccountJpa.builder()
                .bankName(newBankAccount.bankName())
                .alias(newBankAccount.alias())
                .iban(newBankAccount.iban())
                .build();

        var bankAccount = this.bankAccountJpaRepository.save(newBankAccountJpa);

        return BankAccountJpaUtils.convertBankAccountJpaToBankAccount(bankAccount);
    }
}
