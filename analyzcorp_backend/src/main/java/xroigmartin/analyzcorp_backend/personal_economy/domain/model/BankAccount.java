package xroigmartin.analyzcorp_backend.personal_economy.domain.model;

public record BankAccount(
    Integer id,
    String bankName,
    String iban,
    String alias
) {}
