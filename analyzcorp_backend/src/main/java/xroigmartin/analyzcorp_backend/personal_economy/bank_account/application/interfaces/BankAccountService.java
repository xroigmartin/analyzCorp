package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces;

import java.util.List;

import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.BankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.CreateBankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.UpdateBankAccountDTO;

public interface BankAccountService {

    List<BankAccountDTO> findAllBankAccount();
    BankAccountDTO createBankAccount(CreateBankAccountDTO createBankAccountDTO);
    BankAccountDTO updateBankAccount(UpdateBankAccountDTO updateBankAccountDTO, Integer idBankAccount);
}
