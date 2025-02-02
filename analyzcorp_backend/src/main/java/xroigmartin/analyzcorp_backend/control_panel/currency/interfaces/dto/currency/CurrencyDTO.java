package xroigmartin.analyzcorp_backend.control_panel.currency.interfaces.dto.currency;

import lombok.Builder;

@Builder
public record CurrencyDTO(
        String code,
        String name
) {}
