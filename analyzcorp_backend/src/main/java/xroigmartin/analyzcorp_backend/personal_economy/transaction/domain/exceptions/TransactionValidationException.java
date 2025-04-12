package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions;

import java.io.Serial;

public class TransactionValidationException extends TransactionDomainException {

    @Serial
    private static final long serialVersionUID = 5831609292587307508L;

    public TransactionValidationException(String message) {
        super(message);
    }
}
