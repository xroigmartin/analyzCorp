package xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.enums.TransactionType;

@Builder
public record Transaction (
        Long id,
        BigDecimal amount,
        String currency,
        OffsetDateTime date,
        TransactionType type,
        String description,
        Long accountId,
        OffsetDateTime createdAt,
        String createdBy,
        OffsetDateTime updatedAt,
        String updatedBy
){}
