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
        return this.accountJpaRepository.findAll()
                .stream()
                .map(AccountJpaUtils::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findAccountById(Long id) {
        var accountJpa = this.accountJpaRepository.findById(id);

        return accountJpa.map(AccountJpaUtils::toDomain);
    }

    @Override
    @Transactional
    public Account save(Account account) {
        var newBankAccountJpa = AccountJpa.create(null, account.getBankName(), account.getIban(),
                account.getAlias(), account.getCreatedBy(), account.getUpdatedBy(), account.getCreatedAt(), account.getUpdatedAt());

        var bankAccount = this.accountJpaRepository.save(newBankAccountJpa);

        return AccountJpaUtils.toDomain(bankAccount);
    }
}
