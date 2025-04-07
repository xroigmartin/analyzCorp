package xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions;

import java.io.Serial;

public class AccountValidationException extends AccountException{

    @Serial
    private static final long serialVersionUID = -8146092435639788834L;

    public AccountValidationException(String message) {
        super(message);
    }
}
