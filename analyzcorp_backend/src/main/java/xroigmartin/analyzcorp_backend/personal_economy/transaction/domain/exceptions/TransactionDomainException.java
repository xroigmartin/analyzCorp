package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions;

import java.io.Serial;

public abstract class TransactionDomainException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -7999031910084862406L;

    public TransactionDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionDomainException(String message) {
        super(message);
    }
}
