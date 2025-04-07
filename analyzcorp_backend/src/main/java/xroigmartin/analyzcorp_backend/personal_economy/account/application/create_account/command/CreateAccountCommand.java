package xroigmartin.analyzcorp_backend.personal_economy.account.application.create_account.command;

public record CreateAccountCommand(
        String bankName,
        String iban,
        String alias,
        String createdBy
) {
    public static CreateAccountCommand create(String bankName, String iban, String alias, String createdBy) {
        return new CreateAccountCommand(bankName, iban, alias, createdBy);
    }
}
