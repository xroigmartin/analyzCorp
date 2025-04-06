package xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.command.CreateBudgetCommand;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;

public interface CreateBudgetUseCase {

    Budget handle(CreateBudgetCommand createBudgetCommand);
}
