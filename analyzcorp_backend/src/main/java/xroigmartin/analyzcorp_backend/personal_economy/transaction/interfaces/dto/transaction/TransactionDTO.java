package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.CategoryDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransactionDTO (
        Long id,
        BigDecimal amount,
        String currency,
        OffsetDateTime date,
        TransactionType type,
        String description,
        CategoryDTO category,
        Long accountId,
        OffsetDateTime createdAt,
        String createdBy,
        OffsetDateTime updatedAt,
        String updatedBy
) {}
