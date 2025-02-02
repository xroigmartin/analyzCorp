package xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.UpdateAccountDTO;

@UtilityClass
public class AccountControllerUtils {

    public static final String ACCOUNT_PATH = PERSONAL_ECONOMY_PATH + "/account";
    public static final String SUCCESS_FIND_ALL_BANK_ACCOUNT = "Find all bank accounts";
    public static final String SUCCESS_GET_ACCOUNT_BY_ID = "Get account by id";
    public static final String SUCCESS_CREATE_BANK_ACCOUNT = "Create bank accounts";

    public static AccountDTO convertAccountToAccountDTO(Account account){
        return AccountDTO.builder()
                .id(account.id())
                .bankName(account.bankName())
                .iban(account.iban())
                .alias(account.alias())
                .createdAt(account.createdAt())
                .createdBy(account.createdBy())
                .updatedAt(account.updatedAt())
                .updatedBy(account.updatedBy())
                .build();
    }

    public static Account convertCreateAccountDtoToAccount(CreateAccountDTO createAccountDTO){
        return Account.builder()
                .bankName(createAccountDTO.bankName())
                .iban(createAccountDTO.iban())
                .alias(createAccountDTO.alias())
                .build();
    }

    public static Account convertUpdateAccountDtoToAccount(UpdateAccountDTO updateAccountDTO, long id){
        return Account.builder()
                .id(id)
                .bankName(updateAccountDTO.bankName())
                .iban(updateAccountDTO.iban())
                .alias(updateAccountDTO.alias())
                .build();
    }
}
