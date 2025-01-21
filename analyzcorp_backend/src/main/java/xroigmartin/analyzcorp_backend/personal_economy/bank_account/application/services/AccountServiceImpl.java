package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.UpdateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.utils.AccountUtils;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.exceptions.AccountNotFoundByIdException;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.service.AccountJpaService;

import java.time.Instant;
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
    public AccountDTO findAccountById(long id) {
        var account = accountJpaService.findAccountById(id);

        return account.map(AccountUtils::convertBankAccountToBankAccountDTO)
                .orElseThrow(() -> new AccountNotFoundByIdException(id));
    }

    @Override
    @Transactional
    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        var newBankAccount = Account.builder()
                .bankName(createAccountDTO.bankName())
                .iban(createAccountDTO.iban())
                .alias(createAccountDTO.alias())
                .createdBy("SYSTEM")
                .createdAt(Instant.now())
                .updatedBy("SYSTEM")
                .updatedAt(Instant.now())
                .build();

        var bankAccount = accountJpaService.createAccount(newBankAccount);

        return AccountUtils.convertBankAccountToBankAccountDTO(bankAccount);
    }

    @Override
    public AccountDTO updateAccount(UpdateAccountDTO updateAccountDTO, long idAccount) {

        var oldAccount = this.findAccountById(idAccount);

        var bankAccountUpdateInfo = Account.builder()
                .id(oldAccount.id())
                .bankName(updateAccountDTO.bankName())
                .iban(updateAccountDTO.iban())
                .alias(updateAccountDTO.alias())
                .createdBy(oldAccount.createdBy())
                .createdAt(oldAccount.createdAt())
                .updatedBy("SYSTEM")
                .updatedAt(Instant.now())
                .build();

        var bankAccount = accountJpaService.updateAccount(bankAccountUpdateInfo);

        return AccountUtils.convertBankAccountToBankAccountDTO(bankAccount);
    }

}
