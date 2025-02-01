package xroigmartin.analyzcorp_backend.personal_economy.transasction.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.infrastructure.jpa.domain.TransactionJpa;

@UtilityClass
public class TransactionJpaUtils {

    public static TransactionJpa convertToTransactionJpa(Transaction transaction) {
        return TransactionJpa.builder()
                .amount(transaction.amount())
                .currency(transaction.currency())
                .date(transaction.date())
                .type(transaction.type())
                .description(transaction.description())
                .accountJpa(AccountJpa.builder().id(transaction.accountId()).build())
                .createdAt(transaction.createdAt())
                .createdBy(transaction.createdBy())
                .updatedAt(transaction.updatedAt())
                .updatedBy(transaction.updatedBy())
                .build();
    }

    public static Transaction convertToTransaction(TransactionJpa transactionJpa) {
        return Transaction.builder()
                .id(transactionJpa.getId())
                .amount(transactionJpa.getAmount())
                .currency(transactionJpa.getCurrency())
                .date(transactionJpa.getDate())
                .type(transactionJpa.getType())
                .description(transactionJpa.getDescription())
                .accountId(transactionJpa.getAccountJpa().getId())
                .createdAt(transactionJpa.getCreatedAt())
                .createdBy(transactionJpa.getCreatedBy())
                .updatedAt(transactionJpa.getUpdatedAt())
                .updatedBy(transactionJpa.getUpdatedBy())
                .build();
    }
}
