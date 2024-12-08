package xroigmartin.analyzcorp_backend.personal_economy.application.interfaces;

import xroigmartin.analyzcorp_backend.personal_economy.application.dto.BankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.application.dto.CreateBankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.application.dto.UpdateBankAccountDTO;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDTO> findAllBankAccount();
    BankAccountDTO createBankAccount(CreateBankAccountDTO createBankAccountDTO);
    BankAccountDTO updateBankAccount(UpdateBankAccountDTO updateBankAccountDTO, Integer idBankAccount);
}
