package xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency;

import java.io.Serial;

public class CurrencyValidationException extends CurrencyDomainException{

    @Serial
    private static final long serialVersionUID = -5492835905586812165L;

    public CurrencyValidationException(String message) {
        super(message);
    }
}
