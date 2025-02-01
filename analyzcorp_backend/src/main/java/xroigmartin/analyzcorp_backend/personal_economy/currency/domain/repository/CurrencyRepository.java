package xroigmartin.analyzcorp_backend.personal_economy.currency.domain.repository;

import java.util.List;

import xroigmartin.analyzcorp_backend.personal_economy.currency.domain.model.Currency;

public interface CurrencyRepository {

    List<Currency> findAllCurrencies();
    Currency findCurrencyByCode(String currencyCode);
}
