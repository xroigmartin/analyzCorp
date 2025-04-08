package xroigmartin.analyzcorp_backend.control_panel.currency.application.find_all_currencies.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_all_currencies.use_case.FindAllCurrenciesUseCase;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.repository.CurrencyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllCurrenciesService implements FindAllCurrenciesUseCase {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> handle() {
        return currencyRepository.findAllCurrencies();
    }
}
