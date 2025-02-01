package xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.dto.currency;

import lombok.Builder;

@Builder
public record CurrencyDTO(
        String code,
        String name
) {}
