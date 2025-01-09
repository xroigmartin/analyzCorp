package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model;

import lombok.Builder;

@Builder
public record Account(
    Long id,
    String bankName,
    String iban,
    String alias
) {}
