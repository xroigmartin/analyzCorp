package xroigmartin.analyzcorp_backend.personal_economy.budget.application.update_budget_amount.command;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateBudgetAmountCommand(
  Long budgetId,
  BigDecimal newAmount
){}
