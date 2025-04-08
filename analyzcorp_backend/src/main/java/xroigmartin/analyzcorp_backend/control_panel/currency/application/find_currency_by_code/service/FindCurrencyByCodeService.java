package xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.command.FindCurrencyByCodeCommand;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.use_case.FindCurrencyByCodeUseCase;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency.CurrencyNotFoundException;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency.CurrencyValidationException;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.repository.CurrencyRepository;

@Service
@AllArgsConstructor
public class FindCurrencyByCodeService implements FindCurrencyByCodeUseCase {

    private final CurrencyRepository currencyRepository;

    @Override
    public Currency handle(FindCurrencyByCodeCommand command) {
        if(StringUtils.isBlank(command.code())) {
            throw new CurrencyValidationException("Code of currency is mandatory for find currency");
        }

        return this.currencyRepository.findCurrencyByCode(command.code())
                .orElseThrow(() -> new CurrencyNotFoundException(command.code()));
    }
}
