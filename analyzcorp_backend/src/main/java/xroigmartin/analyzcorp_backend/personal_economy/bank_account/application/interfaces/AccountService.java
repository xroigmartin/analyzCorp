package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.UpdateAccountDTO;

import java.util.List;

public interface AccountService {

    List<AccountDTO> findAllAccount();
    AccountDTO findAccountById(long id);
    AccountDTO createAccount(CreateAccountDTO createAccountDTO);
    AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO, long idAccount);
}
