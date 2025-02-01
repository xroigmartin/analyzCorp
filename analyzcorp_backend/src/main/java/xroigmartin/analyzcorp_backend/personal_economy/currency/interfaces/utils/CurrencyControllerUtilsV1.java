package xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.utils;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.dto.currency.CurrencyDTO;

@UtilityClass
public class CurrencyControllerUtilsV1 {

    public static final String CURRENCIES_PATH = PERSONAL_ECONOMY_PATH + "/currencies";
    public static final String SUCCESS_FIND_ALL_CURRENCIES = "Find all currencies";
    public static final String SUCCESS_GET_CURRENCY_BY_CODE = "Get account by code";

    public static CurrencyDTO convertToCurrencyDTO(Currency currency) {
        return CurrencyDTO.builder()
                .code(currency.code())
                .name(currency.name())
                .build();
    }
}
