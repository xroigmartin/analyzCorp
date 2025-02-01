package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
