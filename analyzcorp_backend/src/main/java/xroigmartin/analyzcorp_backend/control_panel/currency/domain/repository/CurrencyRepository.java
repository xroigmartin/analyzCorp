package xroigmartin.analyzcorp_backend.control_panel.currency.domain.repository;

import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {

    List<Currency> findAllCurrencies();
    Optional<Currency> findCurrencyByCode(String currencyCode);
}
