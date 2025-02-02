package xroigmartin.analyzcorp_backend.personal_economy.account.application.services.impl;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.services.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions.AccountNotFoundByIdException;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.service.AccountJpaService;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountJpaService accountJpaService;

    @Override
    public List<Account> findAllAccount() {
        return accountJpaService.findAllAccount();
    }

    @Override
    public Account findAccountById(long id) {
        return accountJpaService.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundByIdException(id));
    }

    @Override
    public Account createAccount(Account createAccount) {

        var newAccountInfo = Account.builder()
                .bankName(createAccount.bankName())
                .iban(createAccount.iban())
                .alias(createAccount.alias())
                .createdBy("SYSTEM")
                .createdAt(Instant.now())
                .updatedBy("SYSTEM")
                .updatedAt(Instant.now())
                .build();

        return accountJpaService.createAccount(newAccountInfo);
    }

    @Override
    public Account updateAccount(Account updateAccount) {

        var oldAccount = this.findAccountById(updateAccount.id());

        var bankAccountUpdateInfo = Account.builder()
                .id(updateAccount.id())
                .bankName(updateAccount.bankName())
                .iban(updateAccount.iban())
                .alias(updateAccount.alias())
                .createdBy(oldAccount.createdBy())
                .createdAt(oldAccount.createdAt())
                .updatedBy("SYSTEM")
                .updatedAt(Instant.now())
                .build();

        return accountJpaService.updateAccount(bankAccountUpdateInfo);
    }

}
