package xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.use_case;

import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.command.FindCurrencyByCodeCommand;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;

public interface FindCurrencyByCodeUseCase {

    Currency handle(FindCurrencyByCodeCommand command);
}
