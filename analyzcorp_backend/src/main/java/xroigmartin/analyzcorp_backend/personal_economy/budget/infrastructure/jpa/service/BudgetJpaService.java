package xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository.BudgetRepository;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.domain.BudgetJpa;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.repository.BudgetJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.utils.BudgetJpaSpecificationUtils;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.utils.BudgetJpaUtils;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository.CategoryJpaRepository;
import xroigmartin.analyzcorp_backend.shared.domain.exceptions.NoFiltersProvidedException;

import java.util.List;

@Component
@Transactional
@AllArgsConstructor
public class BudgetJpaService implements BudgetRepository {

    private final BudgetJpaRepository budgetJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Budget> getBudgets(int year) {
        Specification<BudgetJpa> spec = Specification.where(null);
        var hasFilters = false;

        if (year >= 0) {
            spec = spec.and(BudgetJpaSpecificationUtils.budgetsOfYear(year));
            hasFilters = true;
        }

        if(!hasFilters){
            throw new NoFiltersProvidedException("At least one filter is required");
        }

        var budgetsJpa = budgetJpaRepository.findAll(spec);

        return budgetsJpa.parallelStream().map(BudgetJpaUtils::convertBudgetJpaToBudget).toList();
    }

    @Override
    @Transactional
    public List<Budget> saveBudgets(List<Budget> budgets) {
        var categoriesJpa = categoryJpaRepository.findAll();

        List<BudgetJpa> budgetsJpa = budgets.parallelStream()
                .map(budget -> {
                    var categoryJpa = categoriesJpa.stream()
                            .filter(category -> category.getId().equals(budget.category().id()))
                            .findFirst()
                            .orElse(null);
                    return BudgetJpaUtils.convertBudgetToBudgetJpa(budget, categoryJpa);
                })
                .toList();

        var budgetSaved = budgetJpaRepository.saveAll(budgetsJpa);

        return budgetSaved.parallelStream()
                .map(BudgetJpaUtils::convertBudgetJpaToBudget)
                .toList();
    }
}
