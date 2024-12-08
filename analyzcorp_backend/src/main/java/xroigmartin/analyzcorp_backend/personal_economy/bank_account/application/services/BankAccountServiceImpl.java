package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.BankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.CreateBankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.UpdateBankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.BankAccountService;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.utils.BankAccountUtils;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.service.BankAccountJpaService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountJpaService bankAccountJpaService;

    @Override
    @Transactional(readOnly = true)
    public List<BankAccountDTO> findAllBankAccount() {
        return bankAccountJpaService.findAllBankAccount()
                .stream()
                .map(BankAccountUtils::convertBankAccountToBankAccountDTO)
                .toList();
    }

    @Override
    @Transactional
    public BankAccountDTO createBankAccount(CreateBankAccountDTO createBankAccountDTO) {
        var newBankAccount = new BankAccount(
                null,
                createBankAccountDTO.bankName(),
                createBankAccountDTO.iban(),
                createBankAccountDTO.alias());

        var bankAccount = bankAccountJpaService.createBankAccount(newBankAccount);

        return BankAccountUtils.convertBankAccountToBankAccountDTO(bankAccount);
    }

    @Override
    public BankAccountDTO updateBankAccount(UpdateBankAccountDTO updateBankAccountDTO, Integer idBankAccount) {
        var bankAccountUpdateInfo = new BankAccount(
                idBankAccount,
                updateBankAccountDTO.bankName(),
                updateBankAccountDTO.iban(),
                updateBankAccountDTO.alias()
        );

        var bankAccount = bankAccountJpaService.updateBankAccount(bankAccountUpdateInfo, idBankAccount);

        return BankAccountUtils.convertBankAccountToBankAccountDTO(bankAccount);
    }

}
