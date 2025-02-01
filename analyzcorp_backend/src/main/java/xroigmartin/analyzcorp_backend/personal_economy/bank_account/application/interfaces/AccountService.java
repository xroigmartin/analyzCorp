package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account.UpdateAccountDTO;

import java.util.List;

public interface AccountService {

    List<AccountDTO> findAllAccount();
    AccountDTO findAccountById(long id);
    AccountDTO createAccount(CreateAccountDTO createAccountDTO);
    AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO, long idAccount);
}
