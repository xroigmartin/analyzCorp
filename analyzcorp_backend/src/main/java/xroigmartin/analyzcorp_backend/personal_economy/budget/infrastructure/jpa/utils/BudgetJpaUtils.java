package xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.domain.BudgetJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;

@UtilityClass
public class BudgetJpaUtils {

    public static Budget toDomain(BudgetJpa entity) {

        var category = CategoryJpaUtils.convertCategoryJpaToCategory(entity.getCategory());

        return Budget.builder()
                .id(entity.getId())
                .category(category)
                .amount(new AmountValueObject(entity.getAmount()))
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public static BudgetJpa toEntity(Budget budget) {

        var categoryJpa = CategoryJpa.builder()
                .id(budget.getCategory().id())
                .build();

        return BudgetJpa.builder()
                .id(budget.getId())
                .category(categoryJpa)
                .amount(budget.getAmount().value())
                .startDate(budget.getStartDate())
                .endDate(budget.getEndDate())
                .createdAt(budget.getCreatedAt())
                .createdBy(budget.getCreatedBy())
                .updatedAt(budget.getUpdatedAt())
                .updatedBy(budget.getUpdatedBy())
                .build();
    }
}
