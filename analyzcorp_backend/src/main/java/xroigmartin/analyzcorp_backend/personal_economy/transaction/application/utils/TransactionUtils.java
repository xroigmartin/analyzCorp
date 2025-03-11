package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.UpdateTransactionDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class TransactionUtils {

    public static Transaction convertCreateTransactionToTransaction(CreateTransactionDTO createTransaction, Category category) {

        var dateTransaction = createTransaction.date().toInstant().atOffset(ZoneOffset.UTC);

        return Transaction.builder()
                .amount(createTransaction.amount())
                .currency(createTransaction.currency())
                .date(dateTransaction)
                .type(createTransaction.type())
                .category(category)
                .description(createTransaction.description())
                .accountId(createTransaction.accountId())
                .createdAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                .createdBy("SYSTEM")
                .updatedAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                .updatedBy("SYSTEM")
                .build();
    }

    public static Transaction convertUpdateTransactionToTransaction(UpdateTransactionDTO updateTransaction, Transaction oldTransaction, Category category) {

        var dateTransaction = updateTransaction.date().toInstant().atOffset(ZoneOffset.UTC);
        var amount = (TransactionType.EXPENSE.equals(updateTransaction.type()) && updateTransaction.amount().compareTo(BigDecimal.ZERO) > 0) ?
                        updateTransaction.amount().negate()
                        : updateTransaction.amount();

        return Transaction.builder()
                .id(oldTransaction.id())
                .amount(amount)
                .currency(updateTransaction.currency())
                .date(dateTransaction)
                .type(updateTransaction.type())
                .description(updateTransaction.description())
                .category(category)
                .accountId(updateTransaction.accountId())
                .createdAt(oldTransaction.createdAt())
                .createdBy(oldTransaction.createdBy())
                .updatedAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                .updatedBy("SYSTEM")
                .build();
    }
}
