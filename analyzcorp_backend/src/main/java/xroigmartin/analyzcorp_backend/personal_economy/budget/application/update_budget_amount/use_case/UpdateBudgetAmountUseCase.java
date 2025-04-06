package xroigmartin.analyzcorp_backend.personal_economy.budget.application.update_budget_amount.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.budget.application.update_budget_amount.command.UpdateBudgetAmountCommand;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;

public interface UpdateBudgetAmountUseCase {
    Budget handle(UpdateBudgetAmountCommand command);
}
