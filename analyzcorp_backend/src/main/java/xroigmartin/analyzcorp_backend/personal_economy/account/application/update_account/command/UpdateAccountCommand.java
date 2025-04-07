package xroigmartin.analyzcorp_backend.personal_economy.account.application.update_account.command;

public record UpdateAccountCommand(
        Long id,
        String bankName,
        String iban,
        String alias,
        String updateBy
) {
    public static UpdateAccountCommand create(Long id, String bankName, String iban, String alias, String updateBy) {
        return new UpdateAccountCommand(id, bankName, iban, alias, updateBy);
    }
}
