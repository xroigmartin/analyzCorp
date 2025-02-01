package xroigmartin.analyzcorp_backend.personal_economy.currency.domain.exceptions.currency;

import java.io.Serial;

public class CurrencyNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3727938250519710221L;

    public CurrencyNotFoundException(String currencyCode) {
        super(String.format("Not found currency with code: %s", currencyCode));
    }
}
