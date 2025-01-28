package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.AccountJpa;

@UtilityClass
public class AccountJpaUtils {

    public static Account convertAccountJpaToAccount(AccountJpa accountJpa){
        return Account.builder()
                .id(accountJpa.getId())
                .bankName(accountJpa.getBankName())
                .iban(accountJpa.getIban())
                .alias(accountJpa.getAlias())
                .createdAt(accountJpa.getCreatedAt())
                .createdBy(accountJpa.getCreatedBy())
                .updatedAt(accountJpa.getUpdatedAt())
                .updatedBy(accountJpa.getUpdatedBy())
                .build();
    }
}
