package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions;

import java.io.Serial;

public class BudgetValidationExceptionBudget extends BudgetDomainException {

    @Serial
    private static final long serialVersionUID = 3761298043921860467L;

    public BudgetValidationExceptionBudget(String message) {
        super(message);
    }
}
