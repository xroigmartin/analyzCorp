package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Account(
    Long id,
    String bankName,
    String iban,
    String alias,
    String createdBy,
    Instant createdAt,
    String updatedBy,
    Instant updatedAt
) {}
