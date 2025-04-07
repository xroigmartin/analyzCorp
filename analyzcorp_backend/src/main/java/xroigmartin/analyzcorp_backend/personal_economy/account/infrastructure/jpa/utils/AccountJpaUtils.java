package xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;

@UtilityClass
public class AccountJpaUtils {

    public static Account toDomain(AccountJpa accountJpa){
        return Account.create(accountJpa.getId(), accountJpa.getBankName(), accountJpa.getIban(),
                accountJpa.getAlias(), accountJpa.getCreatedBy(), accountJpa.getUpdatedBy(),
                accountJpa.getCreatedAt(), accountJpa.getUpdatedAt());
    }
}
