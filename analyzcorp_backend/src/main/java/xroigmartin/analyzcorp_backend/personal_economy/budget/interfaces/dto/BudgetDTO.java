package xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.dto;

import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.CategoryDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Builder
public record BudgetDTO (
    Long id,
    CategoryDTO category,
    BigDecimal amount,
    LocalDate startDate,
    LocalDate endDate,
    OffsetDateTime createdAt,
    String createdBy,
    OffsetDateTime updatedAt,
    String updatedBy
){}
