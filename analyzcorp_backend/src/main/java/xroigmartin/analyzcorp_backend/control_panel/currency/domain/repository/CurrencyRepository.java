package xroigmartin.analyzcorp_backend.control_panel.currency.domain.repository;

import java.util.List;

import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;

public interface CurrencyRepository {

    List<Currency> findAllCurrencies();
    Currency findCurrencyByCode(String currencyCode);
}
