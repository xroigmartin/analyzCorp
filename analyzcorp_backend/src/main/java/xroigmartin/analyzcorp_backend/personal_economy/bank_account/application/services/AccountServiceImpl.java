package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.UpdateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.utils.AccountUtils;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.service.AccountJpaService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountJpaService accountJpaService;

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> findAllAccount() {
        return accountJpaService.findAllAccount()
                .stream()
                .map(AccountUtils::convertBankAccountToBankAccountDTO)
                .toList();
    }

    @Override
    @Transactional
    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        var newBankAccount = new Account(
                null,
                createAccountDTO.bankName(),
                createAccountDTO.iban(),
                createAccountDTO.alias());

        var bankAccount = accountJpaService.createAccount(newBankAccount);

        return AccountUtils.convertBankAccountToBankAccountDTO(bankAccount);
    }

    @Override
    public AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO, Long idAccount) {
        var bankAccountUpdateInfo = new Account(
                idAccount,
                updateAccountDTO.bankName(),
                updateAccountDTO.iban(),
                updateAccountDTO.alias()
        );

        var bankAccount = accountJpaService.updateAccount(bankAccountUpdateInfo, idAccount);

        return AccountUtils.convertBankAccountToBankAccountDTO(bankAccount);
    }

}
