package xroigmartin.analyzcorp_backend.control_panel.currency.domain.model;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record Currency (
    String code,
    String name
){
    public static Currency create(String code, String name) {
        return new Currency(code, name);
    }
}
