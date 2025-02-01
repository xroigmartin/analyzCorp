package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AccountDTO(
        Long id,
        String bankName,
        String iban,
        String alias,
        String createdBy,
        Instant createdAt,
        String updatedBy,
        Instant updatedAt
) {}
