package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.domain.TransactionJpa;

import java.util.Optional;

@UtilityClass
public class TransactionJpaUtils {

    public static TransactionJpa convertToTransactionJpa(Transaction transaction) {

        var categoryJpa = Optional.ofNullable(transaction.category())
                                    .flatMap(category -> Optional.ofNullable(category.id())
                                                            .map(id -> CategoryJpa.builder().id(id).build()))
                                    .orElse(null);

        return TransactionJpa.builder()
                .amount(transaction.amount())
                .currency(transaction.currency())
                .date(transaction.date())
                .type(transaction.type())
                .description(transaction.description())
                .category(categoryJpa)
                .accountJpa(AccountJpa.builder().id(transaction.accountId()).build())
                .createdAt(transaction.createdAt())
                .createdBy(transaction.createdBy())
                .updatedAt(transaction.updatedAt())
                .updatedBy(transaction.updatedBy())
                .build();
    }

    public static Transaction convertToTransaction(TransactionJpa transactionJpa) {

        var category = CategoryJpaUtils.convertCategoryJpaToCategory(transactionJpa.getCategory());

        return Transaction.builder()
                .id(transactionJpa.getId())
                .amount(transactionJpa.getAmount())
                .currency(transactionJpa.getCurrency())
                .date(transactionJpa.getDate())
                .type(transactionJpa.getType())
                .description(transactionJpa.getDescription())
                .category(category)
                .accountId(transactionJpa.getAccountJpa().getId())
                .createdAt(transactionJpa.getCreatedAt())
                .createdBy(transactionJpa.getCreatedBy())
                .updatedAt(transactionJpa.getUpdatedAt())
                .updatedBy(transactionJpa.getUpdatedBy())
                .build();
    }

    public static TransactionJpa convertToTransactionJpaForUpdate(Transaction transaction) {
        var categoryJpa = Optional.ofNullable(transaction.category())
                .flatMap(category -> Optional.ofNullable(category.id())
                        .map(id -> CategoryJpa.builder().id(id).build()))
                .orElse(null);

        return TransactionJpa.builder()
                .id(transaction.id())
                .amount(transaction.amount())
                .currency(transaction.currency())
                .date(transaction.date())
                .type(transaction.type())
                .description(transaction.description())
                .category(categoryJpa)
                .accountJpa(AccountJpa.builder().id(transaction.accountId()).build())
                .createdAt(transaction.createdAt())
                .createdBy(transaction.createdBy())
                .updatedAt(transaction.updatedAt())
                .updatedBy(transaction.updatedBy())
                .build();
    }
}
