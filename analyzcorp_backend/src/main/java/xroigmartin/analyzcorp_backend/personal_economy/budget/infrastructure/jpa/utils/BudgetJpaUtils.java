package xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.domain.BudgetJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;

@UtilityClass
public class BudgetJpaUtils {

    public static Budget convertBudgetJpaToBudget(BudgetJpa budgetJpa) {

        var category = CategoryJpaUtils.convertCategoryJpaToCategory(budgetJpa.getCategory());

        return Budget.builder()
                .id(budgetJpa.getId())
                .category(category)
                .amount(budgetJpa.getAmount())
                .startDate(budgetJpa.getStartDate())
                .endDate(budgetJpa.getEndDate())
                .createdAt(budgetJpa.getCreatedAt())
                .createdBy(budgetJpa.getCreatedBy())
                .updatedAt(budgetJpa.getUpdatedAt())
                .updatedBy(budgetJpa.getUpdatedBy())
                .build();
    }

    public static BudgetJpa convertBudgetToBudgetJpa(Budget budget, CategoryJpa categoryJpa) {

        return BudgetJpa.builder()
                .id(budget.id())
                .category(categoryJpa)
                .amount(budget.amount())
                .startDate(budget.startDate())
                .endDate(budget.endDate())
                .createdAt(budget.createdAt())
                .createdBy(budget.createdBy())
                .updatedAt(budget.updatedAt())
                .updatedBy(budget.updatedBy())
                .build();
    }
}
