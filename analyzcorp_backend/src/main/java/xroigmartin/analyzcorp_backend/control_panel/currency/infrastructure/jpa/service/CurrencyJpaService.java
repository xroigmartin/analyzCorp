package xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.repository.CurrencyRepository;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.repository.CurrencyJpaRepository;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.utils.CurrencyJpaUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyJpaService implements CurrencyRepository {

    private final CurrencyJpaRepository currencyRepository;

    @Override
    public List<Currency> findAllCurrencies() {
        var currencies = this.currencyRepository.findAll();
        return currencies.stream().map(CurrencyJpaUtils::toDomain).toList();
    }

    @Override
    public Optional<Currency> findCurrencyByCode(String currencyCode) {
        return this.currencyRepository.findById(currencyCode)
                .map(CurrencyJpaUtils::toDomain);
    }
}
