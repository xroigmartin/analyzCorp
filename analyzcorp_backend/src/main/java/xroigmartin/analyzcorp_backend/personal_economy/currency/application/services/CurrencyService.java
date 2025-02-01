package xroigmartin.analyzcorp_backend.personal_economy.currency.application.services;

import java.util.List;

import xroigmartin.analyzcorp_backend.personal_economy.currency.domain.model.Currency;

public interface CurrencyService {

    List<Currency> findAllCurrencies();
    Currency findCurrencyByCode(String currencyCode);
}
