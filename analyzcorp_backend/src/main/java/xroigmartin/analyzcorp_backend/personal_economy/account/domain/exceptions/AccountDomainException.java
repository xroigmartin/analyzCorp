package xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions;

import java.io.Serial;

public abstract class AccountDomainException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -2052064685007629875L;

    public AccountDomainException(String message) {
        super(message);
    }

    public AccountDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
