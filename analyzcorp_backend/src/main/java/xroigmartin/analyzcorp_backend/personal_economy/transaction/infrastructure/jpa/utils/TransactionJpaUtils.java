package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.domain.CurrencyJpa;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.utils.CurrencyJpaUtils;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.utils.AccountJpaUtils;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.domain.TransactionJpa;

@UtilityClass
public class TransactionJpaUtils {

    public static TransactionJpa toEntity(Transaction transaction) {

        return TransactionJpa.builder()
                .amount(transaction.getAmount().value())
                .currency(CurrencyJpa.builder().code(transaction.getCurrency().code()).build())
                .date(transaction.getDate())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .category(CategoryJpa.builder().id(transaction.getCategory().getId()).build())
                .accountJpa(AccountJpa.builder().id(transaction.getAccount().getId()).build())
                .createdAt(transaction.getCreatedAt())
                .createdBy(transaction.getCreatedBy())
                .updatedAt(transaction.getUpdatedAt())
                .updatedBy(transaction.getUpdatedBy())
                .build();
    }

    public static Transaction toDomain(TransactionJpa transactionJpa) {

        var category = CategoryJpaUtils.toDomain(transactionJpa.getCategory());
        var account = AccountJpaUtils.toDomain(transactionJpa.getAccountJpa());
        var currency = CurrencyJpaUtils.toDomain(transactionJpa.getCurrency());

        return Transaction.create(transactionJpa.getId(), new AmountValueObject(transactionJpa.getAmount()), currency,
                transactionJpa.getDate(), transactionJpa.getType(), transactionJpa.getDescription(), category,
                account, transactionJpa.getCreatedAt(), transactionJpa.getCreatedBy(),
                transactionJpa.getUpdatedAt(), transactionJpa.getUpdatedBy());
    }
}
