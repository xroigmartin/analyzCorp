package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.command;

public record FindTransactionByIdCommand(
   Long id
) {
    public static FindTransactionByIdCommand create(Long id) {
        return new FindTransactionByIdCommand(id);
    }
}
