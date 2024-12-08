package xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model;

public record BankAccount(
    Integer id,
    String bankName,
    String iban,
    String alias
) {}
