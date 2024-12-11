package xroigmartin.analyzcorp_backend.personal_economy.utils.validator;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.BankAccountJpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public class BankAccountValidator {
    public static void validateBankAccount(BankAccount bankAccountExpected, BankAccount bankAccountResult) {
        assertEquals(bankAccountExpected.id(), bankAccountResult.id());
        assertEquals(bankAccountExpected.bankName(), bankAccountResult.bankName());
        assertEquals(bankAccountExpected.iban(), bankAccountResult.iban());
        assertEquals(bankAccountExpected.alias(), bankAccountResult.alias());
    }

    public static void validateBankAccount(BankAccountJpa bankAccountJpaExcepted, BankAccount bankAccountResult) {
        assertEquals(bankAccountJpaExcepted.getId(), bankAccountResult.id());
        assertEquals(bankAccountJpaExcepted.getBankName(), bankAccountResult.bankName());
        assertEquals(bankAccountJpaExcepted.getIban(), bankAccountResult.iban());
        assertEquals(bankAccountJpaExcepted.getAlias(), bankAccountResult.alias());

    }
}
