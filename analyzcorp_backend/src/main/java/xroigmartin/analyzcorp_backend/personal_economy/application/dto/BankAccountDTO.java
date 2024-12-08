package xroigmartin.analyzcorp_backend.personal_economy.application.dto;

public record BankAccountDTO(
        Integer id,
        String bankName,
        String iban,
        String alias
) {}
