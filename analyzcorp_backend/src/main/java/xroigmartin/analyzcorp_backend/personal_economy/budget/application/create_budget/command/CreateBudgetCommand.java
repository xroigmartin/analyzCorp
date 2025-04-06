package xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.command;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CreateBudgetCommand(
   Long categoryId,
   Long accountId,
   BigDecimal amount,
   LocalDate startDate,
   LocalDate endDate,
   String createdBy
) {}
