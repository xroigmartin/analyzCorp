package xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions;

import java.io.Serial;

public abstract class AccountException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -2052064685007629875L;

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
