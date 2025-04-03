package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Builder
public record Budget(
        Long id,
        Category category,
        BigDecimal amount,
        LocalDate startDate,
        LocalDate endDate,
        OffsetDateTime createdAt,
        String createdBy,
        OffsetDateTime updatedAt,
        String updatedBy
) {
}
