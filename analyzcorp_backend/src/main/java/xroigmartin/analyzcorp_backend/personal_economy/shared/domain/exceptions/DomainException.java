package xroigmartin.analyzcorp_backend.personal_economy.shared.domain.exceptions;

import java.io.Serial;

public abstract class DomainException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -3492142213795830544L;

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
