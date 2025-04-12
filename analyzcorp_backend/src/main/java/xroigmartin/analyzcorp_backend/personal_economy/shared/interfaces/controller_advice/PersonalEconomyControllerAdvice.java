package xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.exceptions.AmountValueObjectException;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.exceptions.DomainException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class PersonalEconomyControllerAdvice {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomainException(DomainException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Bad request",
                String.format(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AmountValueObjectException.class)
    public ResponseEntity<ApiResponse<Void>> handlePrecondition(DomainException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Precondition error",
                String.format(ex.getMessage()),
                HttpStatus.PRECONDITION_FAILED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.PRECONDITION_FAILED);
    }
}
