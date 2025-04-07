package xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.AccountDTO;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

@UtilityClass
public class AccountControllerUtils {

    public static final String ACCOUNT_PATH = PERSONAL_ECONOMY_PATH + "/account";
    public static final String SUCCESS_FIND_ALL_BANK_ACCOUNT = "Find all bank accounts";
    public static final String SUCCESS_GET_ACCOUNT_BY_ID = "Get account by id";
    public static final String SUCCESS_CREATE_BANK_ACCOUNT = "Create bank accounts";

    public static AccountDTO convertAccountToAccountDTO(Account account){
        return AccountDTO.create(account.getId(), account.getBankName(), account.getIban(),
                account.getAlias(), account.getCreatedBy(), account.getCreatedAt(),
                account.getUpdatedBy(), account.getUpdatedAt());
    }
}
