package xroigmartin.analyzcorp_backend.personal_economy.currency.domain.model;

import lombok.Builder;

@Builder
public record Currency (
    String code,
    String name
){}
