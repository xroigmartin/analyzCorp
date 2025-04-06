package xroigmartin.analyzcorp_backend.personal_economy.budget.application.update_budget_amount.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.update_budget_amount.command.UpdateBudgetAmountCommand;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.update_budget_amount.use_case.UpdateBudgetAmountUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository.BudgetRepository;

@Service
@AllArgsConstructor
public class UpdateBudgetAmountService implements UpdateBudgetAmountUseCase {

    private final BudgetRepository budgetRepository;

    @Override
    public Budget handle(UpdateBudgetAmountCommand command) {
        Budget budget = budgetRepository.findById(command.budgetId())
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        budget.updateAmount(command.newAmount());

        return budgetRepository.save(budget);
    }
}
