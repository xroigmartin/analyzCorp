package xroigmartin.analyzcorp_backend.personal_economy.application.interfaces;

import xroigmartin.analyzcorp_backend.personal_economy.application.dto.BankAccountDTO;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDTO> findAllBankAccount();
}
