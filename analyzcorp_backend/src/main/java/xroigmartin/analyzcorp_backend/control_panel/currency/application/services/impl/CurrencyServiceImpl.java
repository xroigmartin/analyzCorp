package xroigmartin.analyzcorp_backend.control_panel.currency.application.services.impl;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.services.CurrencyService;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.repository.CurrencyRepository;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> findAllCurrencies() {
        return this.currencyRepository.findAllCurrencies();
    }

    @Override
    public Currency findCurrencyByCode(String currencyCode) {
        return this.currencyRepository.findCurrencyByCode(currencyCode);
    }

}
