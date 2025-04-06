package xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.dto;

import java.math.BigDecimal;

public record BudgetAmountUpdateDTO(
   Long id,
   BigDecimal amount
){}
