package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.BankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;

@UtilityClass
public class BankAccountUtils {

    public static BankAccountDTO convertBankAccountToBankAccountDTO(BankAccount bankAccount){
        return new BankAccountDTO(
                bankAccount.id(),
                bankAccount.bankName(),
                bankAccount.iban(),
                bankAccount.alias()
        );
    }
}
