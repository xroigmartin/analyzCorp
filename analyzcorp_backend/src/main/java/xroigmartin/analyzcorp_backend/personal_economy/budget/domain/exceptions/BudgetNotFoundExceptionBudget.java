package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions;

import java.io.Serial;

public class BudgetNotFoundExceptionBudget extends BudgetDomainException {

    @Serial
    private static final long serialVersionUID = -5275518685008873386L;

    public BudgetNotFoundExceptionBudget(Long id) {
        super("Not exists budget with id " + id);
    }
}
