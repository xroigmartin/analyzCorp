package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain.BankAccountJpa;

@UtilityClass
public class BankAccountJpaUtils {

    public static BankAccount convertBankAccountJpaToBankAccount(BankAccountJpa bankAccount){
        return new BankAccount(
                bankAccount.getId(),
                bankAccount.getBankName(),
                bankAccount.getIban(),
                bankAccount.getAlias()
        );
    }
}
