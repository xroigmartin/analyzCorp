package xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object;

import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions.BudgetValidationException;

import java.math.BigDecimal;

public record AmountValueObject(BigDecimal value) {

    public AmountValueObject {
        if (value == null || value.signum() < 0) {
            throw new BudgetValidationException("Amount must be non-negative");
        }
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
