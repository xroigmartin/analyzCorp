package xroigmartin.analyzcorp_backend.personal_economy.domain.model.mock;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.shared.DataFaker;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class BankAccountMock {

    public static BankAccount generateBankAccountMock() {
        return new BankAccount(
                DataFaker.faker().number().positive(),
                DataFaker.faker().word().noun(),
                DataFaker.faker().finance().iban(),
                DataFaker.faker().word().noun());
    }

    public static BankAccount generateBankAccountMockWithoutId() {
        return new BankAccount(
                null,
                DataFaker.faker().word().noun(),
                DataFaker.faker().finance().iban(),
                DataFaker.faker().word().noun());
    }

    public static BankAccount generateBankAccountMockWithId(int id){
        return new BankAccount(
                id,
                DataFaker.faker().word().noun(),
                DataFaker.faker().finance().iban(),
                DataFaker.faker().word().noun());
    }

    public static List<BankAccount> generateListOfBankAccountsMocks() {
        Random random = new Random();
        var randomElements = random.nextInt(100) + 1;
        return IntStream.range(0,randomElements)
                .mapToObj(BankAccountMock::generateBankAccountMockWithId)
                .collect(Collectors.toList());
    }

}
