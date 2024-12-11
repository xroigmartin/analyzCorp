package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain.mock;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.BankAccountJpa;
import xroigmartin.analyzcorp_backend.shared.DataFaker;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class BankAccountJpaMock {

    public static List<BankAccountJpa> generateListOfBankAccountsJpaMocks() {
        Random random = new Random();
        var randomElements = random.nextInt(100) + 1;
        return IntStream.range(0,randomElements)
                .mapToObj(BankAccountJpaMock::generateBankAccountJpaMockWithId)
                .collect(Collectors.toList());
    }

    public static BankAccountJpa generateBankAccountJpaMockWithId(int id) {
        return BankAccountJpa.builder()
                .id(id)
                .bankName(DataFaker.faker().word().noun())
                .iban(DataFaker.faker().finance().iban())
                .alias(DataFaker.faker().word().noun())
                .build();
    }

    public static BankAccountJpa generateBankAccountJpaMock() {
        return BankAccountJpa.builder()
                .id(DataFaker.faker().number().positive())
                .bankName(DataFaker.faker().word().noun())
                .iban(DataFaker.faker().finance().iban())
                .alias(DataFaker.faker().word().noun())
                .build();
    }

    public static BankAccountJpa convertBankAccountToBankAccountJpa(BankAccount bankAccount) {
        return BankAccountJpa.builder()
                .id(bankAccount.id())
                .bankName(bankAccount.bankName())
                .iban(bankAccount.iban())
                .alias(bankAccount.alias())
                .build();
    }

}
