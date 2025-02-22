package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

@Builder
public record CreateTransactionDTO (
        BigDecimal amount,
        String currency,
        OffsetDateTime date,
        TransactionType type,
        String description,
        Long accountId
){}
