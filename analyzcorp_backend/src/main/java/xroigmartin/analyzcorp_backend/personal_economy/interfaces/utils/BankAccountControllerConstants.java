package xroigmartin.analyzcorp_backend.personal_economy.interfaces.utils;

import lombok.experimental.UtilityClass;

import static xroigmartin.analyzcorp_backend.personal_economy.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

@UtilityClass
public class BankAccountControllerConstants {

    public static final String BANK_ACCOUNT_PATH = PERSONAL_ECONOMY_PATH + "/bank-account";
    public static final String SUCCESS_FIND_ALL_BANK_ACCOUNT = "Find all bank accounts";
}
