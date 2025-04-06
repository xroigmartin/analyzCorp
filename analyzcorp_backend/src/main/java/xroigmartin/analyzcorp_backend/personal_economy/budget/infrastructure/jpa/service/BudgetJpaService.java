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
import xroigmartin.analyzcorp_backend.shared.domain.exceptions.NoFiltersProvidedException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@AllArgsConstructor
public class BudgetJpaService implements BudgetRepository {

    private final BudgetJpaRepository budgetJpaRepository;

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

        return budgetsJpa.parallelStream().map(BudgetJpaUtils::toDomain).toList();
    }

    @Override
    @Transactional
    public List<Budget> saveBudgets(List<Budget> budgets) {
        List<BudgetJpa> budgetsJpa = budgets.parallelStream()
                .map(BudgetJpaUtils::toEntity)
                .toList();

        var budgetSaved = budgetJpaRepository.saveAll(budgetsJpa);

        return budgetSaved.parallelStream()
                .map(BudgetJpaUtils::toDomain)
                .toList();
    }

    @Override
    public Optional<Budget> getBudgetById(Long id) {
        var budgetJpa = this.budgetJpaRepository.findById(id);
        return budgetJpa.map(BudgetJpaUtils::toDomain);
    }

    @Override
    public Budget updateBudgetAmount(Long id, BigDecimal amount) {
        var budgetJpa = this.budgetJpaRepository.getReferenceById(id);

        var updateBudget = BudgetJpa.builder()
                .id(id)
                .category(budgetJpa.getCategory())
                .amount(amount)
                .startDate(budgetJpa.getStartDate())
                .endDate(budgetJpa.getEndDate())
                .createdAt(budgetJpa.getCreatedAt())
                .createdBy(budgetJpa.getCreatedBy())
                .updatedAt(OffsetDateTime.now())
                .updatedBy("SYSTEM")
                .build();

        var budgetJpaUpdated = this.budgetJpaRepository.save(updateBudget);
        return BudgetJpaUtils.toDomain(budgetJpaUpdated);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> findById(Long id) {
        return budgetJpaRepository.findById(id).map(BudgetJpaUtils::toDomain);
    }

    @Override
    @Transactional
    public Budget save(Budget budget) {
        var saved = budgetJpaRepository.save(BudgetJpaUtils.toEntity(budget));
        return BudgetJpaUtils.toDomain(saved);
    }

    @Override
    public List<Budget> findAll() {
        return budgetJpaRepository.findAll().parallelStream().map(BudgetJpaUtils::toDomain).toList();
    }
}
