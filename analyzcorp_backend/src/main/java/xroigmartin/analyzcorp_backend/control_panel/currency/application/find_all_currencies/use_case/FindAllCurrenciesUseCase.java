package xroigmartin.analyzcorp_backend.control_panel.currency.application.find_all_currencies.use_case;

import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;

import java.util.List;

public interface FindAllCurrenciesUseCase {

    List<Currency> handle();
}
