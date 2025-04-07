package xroigmartin.analyzcorp_backend.personal_economy.account.application.create_account.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.create_account.command.CreateAccountCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.create_account.use_case.CreateAccountUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.repository.AccountRepository;

import java.time.Instant;

@Service
@AllArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Account handle(CreateAccountCommand command) {

        var newAccountInfo = Account.create(null, command.bankName(), command.iban(), command.alias(),
                        command.createdBy(), command.createdBy());

        return accountRepository.save(newAccountInfo);
    }
}
