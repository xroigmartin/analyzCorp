package xroigmartin.analyzcorp_backend.personal_economy.account.application.find_all_accounts.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_all_accounts.use_case.FindAllAccountsUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.repository.AccountRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllAccountsService implements FindAllAccountsUseCase {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> handle() {
        return accountRepository.findAllAccount();
    }
}
