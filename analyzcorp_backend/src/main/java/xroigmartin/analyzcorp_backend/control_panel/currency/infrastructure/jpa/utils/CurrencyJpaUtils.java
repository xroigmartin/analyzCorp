package xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.domain.CurrencyJpa;

@UtilityClass
public class CurrencyJpaUtils {

    public static Currency convertToCurrency(CurrencyJpa currencyJpa) {
        return Currency.builder()
                .name(currencyJpa.getName())
                .code(currencyJpa.getCode())
                .build();
    }
}
