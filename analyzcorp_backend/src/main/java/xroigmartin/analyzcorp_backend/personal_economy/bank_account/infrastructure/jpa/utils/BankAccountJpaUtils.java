package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.BankAccountJpa;

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
