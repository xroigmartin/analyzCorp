package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

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
        Category category,
        Long accountId,
        OffsetDateTime createdAt,
        String createdBy,
        OffsetDateTime updatedAt,
        String updatedBy
){}
