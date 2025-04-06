package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions;

import java.io.Serial;

public class BudgetDomainException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 9155175272184738478L;

    public BudgetDomainException(String message){
        super(message);
    }

    public BudgetDomainException(String message, Throwable cause){
        super(message, cause);
    }
}
