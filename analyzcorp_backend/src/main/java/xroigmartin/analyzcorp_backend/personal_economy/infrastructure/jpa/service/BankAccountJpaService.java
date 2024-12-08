package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.domain.repository.ListBankAccountRepository;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.repository.BankAccountJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.utils.BankAccountJpaUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountJpaService implements ListBankAccountRepository {

    private final BankAccountJpaRepository bankAccountJpaRepository;

    @Override
    public List<BankAccount> findAllBankAccount() {
        var bankAccounts = this.bankAccountJpaRepository.findAll();
        return bankAccounts.stream().map(BankAccountJpaUtils::convertBankAccountJpaToBankAccount).toList();
    }
}
