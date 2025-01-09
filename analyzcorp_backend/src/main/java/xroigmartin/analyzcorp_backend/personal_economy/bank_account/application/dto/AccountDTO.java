package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto;

import lombok.Builder;

@Builder
public record AccountDTO(
        Long id,
        String bankName,
        String iban,
        String alias
) {}
