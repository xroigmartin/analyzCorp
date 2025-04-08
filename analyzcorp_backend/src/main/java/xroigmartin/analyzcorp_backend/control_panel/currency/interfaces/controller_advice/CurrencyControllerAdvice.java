package xroigmartin.analyzcorp_backend.control_panel.currency.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency.CurrencyDomainException;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency.CurrencyNotFoundException;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.exceptions.currency.CurrencyValidationException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class CurrencyControllerAdvice {

    @ExceptionHandler(CurrencyDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleCurrencyDomainException(CurrencyDomainException ex) {
        log.warn(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Bad request for currency",
                String.format(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
        log.warn(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Currency not found",
                String.format(ex.getMessage()),
                HttpStatus.NOT_FOUND.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CurrencyValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleCurrencyValidationException(CurrencyValidationException ex) {
        log.warn(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Currency precondition error",
                String.format(ex.getMessage()),
                HttpStatus.PRECONDITION_FAILED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.PRECONDITION_FAILED);
    }
}
