package xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.v1;

import static xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.utils.CurrencyControllerUtilsV1.CURRENCIES_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.utils.CurrencyControllerUtilsV1.SUCCESS_FIND_ALL_CURRENCIES;
import static xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.utils.CurrencyControllerUtilsV1.SUCCESS_GET_CURRENCY_BY_CODE;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.personal_economy.currency.application.services.CurrencyService;
import xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.dto.currency.CurrencyDTO;
import xroigmartin.analyzcorp_backend.personal_economy.currency.interfaces.utils.CurrencyControllerUtilsV1;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestController
@RequestMapping(CURRENCIES_PATH)
@AllArgsConstructor
public class CurrencyControllerV1 {

    public final CurrencyService currencyService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CurrencyDTO>>> findAllCurrencies() {
        var currencies = currencyService.findAllCurrencies();

        var currenciesDtos = currencies.stream().map(CurrencyControllerUtilsV1::convertToCurrencyDTO).toList();

        var apiResponse = ApiResponseHandler.generateSuccess(currenciesDtos, SUCCESS_FIND_ALL_CURRENCIES, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{currencyCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CurrencyDTO>> getCurrencyByCode(@PathVariable String currencyCode) {
        var currency = currencyService.findCurrencyByCode(currencyCode);

        var currencyDto = CurrencyControllerUtilsV1.convertToCurrencyDTO(currency);

        var apiResponse = ApiResponseHandler.generateSuccess(currencyDto, SUCCESS_GET_CURRENCY_BY_CODE, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
