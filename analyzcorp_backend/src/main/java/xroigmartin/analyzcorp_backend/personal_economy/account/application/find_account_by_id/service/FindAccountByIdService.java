package xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions.AccountNotFoundByIdException;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.repository.AccountRepository;

@Service
@AllArgsConstructor
public class FindAccountByIdService implements FindAccountByIdUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Account handle(FindAccountByIdCommand command) {
        return accountRepository.findAccountById(command.id())
                .orElseThrow(() -> new AccountNotFoundByIdException(command.id()));
    }
}
