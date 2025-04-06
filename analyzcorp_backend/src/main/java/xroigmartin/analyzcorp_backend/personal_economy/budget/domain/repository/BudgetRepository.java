package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BudgetRepository {

    List<Budget> getBudgets(int year);

    List<Budget> saveBudgets(List<Budget> budgets);

    Optional<Budget> getBudgetById(Long id);

    Budget updateBudgetAmount(Long id, BigDecimal amount);

    Optional<Budget> findById(Long id);
    Budget save(Budget budget);
    List<Budget> findAll();
}
