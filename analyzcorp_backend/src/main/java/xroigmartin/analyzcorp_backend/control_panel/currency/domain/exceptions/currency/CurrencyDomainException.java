package xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency;

import java.io.Serial;

public class CurrencyDomainException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -612958026116693126L;

    public CurrencyDomainException(String message) {
        super(message);
    }

    public CurrencyDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
