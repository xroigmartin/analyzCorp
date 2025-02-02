package xroigmartin.analyzcorp_backend.control_panel.currency.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency.CurrencyNotFoundException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class CurrencyControllerAdvice {

    @ExceptionHandler({CurrencyNotFoundException.class})
    public ResponseEntity<ApiResponse<Void>> currencyNotFound(CurrencyNotFoundException ex){
        log.error(ex.getMessage(), ex);
        var apiResponseError = ApiResponseHandler.generateError(
                "Currency not found",
                String.format(ex.getMessage()),
                HttpStatus.NOT_FOUND.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.NOT_FOUND);
    }
}
