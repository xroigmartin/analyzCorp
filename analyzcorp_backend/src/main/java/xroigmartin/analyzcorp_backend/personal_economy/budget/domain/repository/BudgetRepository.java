package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;

import java.util.List;

public interface BudgetRepository {

    List<Budget> getBudgets(int year);

    List<Budget> saveBudgets(List<Budget> budgets);
}
