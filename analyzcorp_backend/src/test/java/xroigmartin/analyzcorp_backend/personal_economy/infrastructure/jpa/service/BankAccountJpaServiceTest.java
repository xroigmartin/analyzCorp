package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.BankAccount;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.BankAccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.repository.BankAccountJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.service.BankAccountJpaService;
import xroigmartin.analyzcorp_backend.personal_economy.domain.model.mock.BankAccountMock;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain.mock.BankAccountJpaMock;
import xroigmartin.analyzcorp_backend.personal_economy.utils.validator.BankAccountValidator;
import xroigmartin.analyzcorp_backend.shared.DataFaker;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountJpaServiceTest {

    @InjectMocks
    private BankAccountJpaService bankAccountJpaService;

    @Mock
    private BankAccountJpaRepository bankAccountJpaRepository;

    @DisplayName("Test for return list of bank accounts")
    @Test
    void testListOfBankAccounts() {

        List<BankAccountJpa> bankAccountsJpaMocks = BankAccountJpaMock.generateListOfBankAccountsJpaMocks();

        when(bankAccountJpaRepository.findAll()).thenReturn(bankAccountsJpaMocks);

        List<BankAccount> bankAccountResult = this.bankAccountJpaService.findAllBankAccount();

        bankAccountsJpaMocks.forEach(bankAccountJpa -> {
            Optional<BankAccount> bankAccount = bankAccountResult.stream().filter(bankAccount1 -> bankAccount1.id().equals(bankAccountJpa.getId())).findFirst();
            assertTrue(bankAccount.isPresent());
            BankAccountValidator.validateBankAccount(bankAccountJpa, bankAccount.get()) ;
        });
    }

    @DisplayName("Test for create bank account")
    @Test
    void testCreateBankAccount() {
        BankAccount newBankAccountMock = BankAccountMock.generateBankAccountMockWithoutId();

        BankAccount bankAccountExpected = new BankAccount(
                DataFaker.faker().number().positive(),
                newBankAccountMock.bankName(),
                newBankAccountMock.iban(),
                newBankAccountMock.alias()
        );

        BankAccountJpa bankAccountJpaAfterSave = BankAccountJpaMock.convertBankAccountToBankAccountJpa(bankAccountExpected);

        when(bankAccountJpaRepository.save(any(BankAccountJpa.class))).thenReturn(bankAccountJpaAfterSave);

        BankAccount bankAccountResult = bankAccountJpaService.createBankAccount(newBankAccountMock);

        ArgumentCaptor<BankAccountJpa> captor = ArgumentCaptor.forClass(BankAccountJpa.class);
        verify(bankAccountJpaRepository).save(captor.capture());

        BankAccountJpa bankAccountJpaBeforeMock = captor.getValue();
        assertNull(bankAccountJpaBeforeMock.getId());
        assertEquals(newBankAccountMock.bankName(), bankAccountJpaBeforeMock.getBankName());
        assertEquals(newBankAccountMock.iban(), bankAccountJpaBeforeMock.getIban());
        assertEquals(newBankAccountMock.alias(), bankAccountJpaBeforeMock.getAlias());

        BankAccountValidator.validateBankAccount(bankAccountExpected, bankAccountResult);
    }

    @DisplayName("Test for update bank account when modify bankName")
    @Test
    void testUpdateBankAccount() {
        BankAccount bankAccountMock = BankAccountMock.generateBankAccountMock();
        BankAccountJpa bankAccountJpa = BankAccountJpa.builder()
                .id(bankAccountMock.id())
                .bankName(DataFaker.faker().finance().iban())
                .iban(bankAccountMock.iban())
                .alias(bankAccountMock.alias())
                .build();

        BankAccountJpa bankAccountJpaAfterSave = BankAccountJpaMock.convertBankAccountToBankAccountJpa(bankAccountMock);

        when(bankAccountJpaRepository.getReferenceById(eq(bankAccountMock.id()))).thenReturn(bankAccountJpa);
        when(bankAccountJpaRepository.save(any(BankAccountJpa.class))).thenReturn(bankAccountJpaAfterSave);

        BankAccount bankAccountResult = bankAccountJpaService.updateBankAccount(bankAccountMock, bankAccountMock.id());

        ArgumentCaptor<BankAccountJpa> captor = ArgumentCaptor.forClass(BankAccountJpa.class);
        verify(bankAccountJpaRepository).save(captor.capture());

        BankAccountJpa bankAccountJpaBeforeMock = captor.getValue();
        assertEquals(bankAccountMock.id(), bankAccountJpaBeforeMock.getId());
        assertEquals(bankAccountMock.bankName(), bankAccountJpaBeforeMock.getBankName());
        assertEquals(bankAccountMock.iban(), bankAccountJpaBeforeMock.getIban());
        assertEquals(bankAccountMock.alias(), bankAccountJpaBeforeMock.getAlias());

        BankAccountValidator.validateBankAccount(bankAccountMock, bankAccountResult);
    }

}
