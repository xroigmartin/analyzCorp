package xroigmartin.analyzcorp_backend.personal_economy.account.application.update_account.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.update_account.command.UpdateAccountCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.update_account.use_case.UpdateAccountUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions.AccountValidationException;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.repository.AccountRepository;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class UpdateAccountService implements UpdateAccountUseCase {

    private final AccountRepository accountRepository;
    private final FindAccountByIdUseCase findAccountByIdUseCase;

    @Override
    public Account handle(UpdateAccountCommand command) {

        if(command.id() == null){
            throw new AccountValidationException("Id of account is mandatory for updated");
        }

        var findAccountByIdCommand = FindAccountByIdCommand.create(command.id());
        var oldAccount = findAccountByIdUseCase.handle(findAccountByIdCommand);


        var bankAccountUpdateInfo = Account.create(oldAccount.getId(), command.bankName(), command.iban(),
                command.alias(), oldAccount.getCreatedBy(), command.updateBy(),
                oldAccount.getCreatedAt(), OffsetDateTime.now());

        return accountRepository.save(bankAccountUpdateInfo);
    }
}
