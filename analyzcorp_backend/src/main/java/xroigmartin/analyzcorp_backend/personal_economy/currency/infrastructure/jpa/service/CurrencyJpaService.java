package xroigmartin.analyzcorp_backend.personal_economy.currency.infrastructure.jpa.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.currency.domain.exceptions.currency.CurrencyNotFoundException;
import xroigmartin.analyzcorp_backend.personal_economy.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.personal_economy.currency.domain.repository.CurrencyRepository;
import xroigmartin.analyzcorp_backend.personal_economy.currency.infrastructure.jpa.repository.CurrencyJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.currency.infrastructure.jpa.utils.CurrencyJpaUtils;

@Service
@AllArgsConstructor
public class CurrencyJpaService implements CurrencyRepository {

    private final CurrencyJpaRepository currencyRepository;

    @Override
    public List<Currency> findAllCurrencies() {
        var currencies = this.currencyRepository.findAll();
        return currencies.stream().map(CurrencyJpaUtils::convertToCurrency).toList();
    }

    @Override
    public Currency findCurrencyByCode(String currencyCode) {
        try {
            var currency = this.currencyRepository.getReferenceById(currencyCode);
            return CurrencyJpaUtils.convertToCurrency(currency);
        }
        catch(EntityNotFoundException ex){
            throw new CurrencyNotFoundException(currencyCode);
        }
    }
}
