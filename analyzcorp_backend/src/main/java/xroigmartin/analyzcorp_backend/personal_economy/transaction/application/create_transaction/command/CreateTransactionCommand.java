package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.command;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CreateTransactionCommand(
        BigDecimal amount,
        String currency,
        Long categoryId,
        OffsetDateTime date,
        TransactionType type,
        String description,
        Long accountId,
        String createdBy
) {
    public static CreateTransactionCommand create(BigDecimal amount, String currency, Long categoryId, OffsetDateTime date,
                                                  TransactionType type,  String description, Long accountId, String createdBy) {
        return new CreateTransactionCommand(amount, currency, categoryId, date, type, description, accountId, createdBy);
    }
}
