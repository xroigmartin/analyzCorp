package xroigmartin.analyzcorp_backend.control_panel.currency.domain.model;

import lombok.Builder;

@Builder
public record Currency (
    String code,
    String name
){}
