package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record CreateTransactionDTO (
        BigDecimal amount,
        String currency,
        Long categoryId,
        OffsetDateTime date,
        TransactionType type,
        String description,
        Long accountId
){}
