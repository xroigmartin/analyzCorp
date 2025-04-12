package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.command;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record UpdateTransactionCommand(
    Long id,
    BigDecimal amount,
    String currency,
    Long categoryId,
    OffsetDateTime date,
    TransactionType type,
    String description,
    Long accountId,
    String updatedBy
) {
    public static UpdateTransactionCommand create(Long id, BigDecimal amount, String currency, Long categoryId,
                                                  OffsetDateTime date, TransactionType type, String description,
                                                  Long accountId, String updatedBy) {
        return new UpdateTransactionCommand(id, amount, currency, categoryId, date, type, description, accountId, updatedBy);
    }
}
