package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Account;

@UtilityClass
public class AccountUtils {

    public static AccountDTO convertBankAccountToBankAccountDTO(Account account){
        return AccountDTO.builder()
                .id(account.id())
                .bankName(account.bankName())
                .iban(account.iban())
                .alias(account.alias())
                .createdAt(account.createdAt())
                .createdBy(account.createdBy())
                .updatedAt(account.updatedAt())
                .updatedBy(account.updatedBy())
                .build();
    }
}
