package xroigmartin.analyzcorp_backend.personal_economy.budget.application.services;

import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;

import java.util.List;

public interface BudgetService {

    List<Budget> getAllBudgets(Integer year);
}
