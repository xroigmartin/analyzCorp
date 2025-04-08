package xroigmartin.analyzcorp_backend.control_panel.currency.interfaces.dto.currency;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CurrencyDTO(
        String code,
        String name
) {
    public static CurrencyDTO create(String code, String name) {
        return new CurrencyDTO(code, name);
    }
}
