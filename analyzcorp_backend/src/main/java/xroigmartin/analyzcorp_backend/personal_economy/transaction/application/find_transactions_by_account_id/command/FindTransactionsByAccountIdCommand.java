package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.command;

public record FindTransactionsByAccountIdCommand(
    Long accountId
) {
    public static FindTransactionsByAccountIdCommand create(Long id) {
        return new FindTransactionsByAccountIdCommand(id);
    }
}
