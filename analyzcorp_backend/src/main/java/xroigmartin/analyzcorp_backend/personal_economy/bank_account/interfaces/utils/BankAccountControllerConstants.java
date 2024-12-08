package xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BankAccountControllerConstants {

    public static final String BANK_ACCOUNT_PATH = PERSONAL_ECONOMY_PATH + "/bank-account";
    public static final String SUCCESS_FIND_ALL_BANK_ACCOUNT = "Find all bank accounts";
    public static final String SUCCESS_CREATE_BANK_ACCOUNT = "Create bank accounts";
}
