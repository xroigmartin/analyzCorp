package xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command;

public record FindAccountByIdCommand(Long accountId) {

    public static FindAccountByIdCommand create(Long accountId){
        return new FindAccountByIdCommand(accountId);
    }

}
