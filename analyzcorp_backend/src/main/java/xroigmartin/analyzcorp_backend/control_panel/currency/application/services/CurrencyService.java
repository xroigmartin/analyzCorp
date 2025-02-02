package xroigmartin.analyzcorp_backend.control_panel.currency.application.services;

import java.util.List;

import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;

public interface CurrencyService {

    List<Currency> findAllCurrencies();
    Currency findCurrencyByCode(String currencyCode);
}
