package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.TransactionDomainException;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.TransactionNotFoundByIdException;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.TransactionValidationException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class TransactionControllerAdvice {

    @ExceptionHandler(TransactionDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleTransactionDomainException(TransactionDomainException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Bad request for transaction",
                String.format(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundByIdException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(TransactionNotFoundByIdException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Transaction not found",
                String.format(ex.getMessage()),
                HttpStatus.NOT_FOUND.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleTransactionValidationException(TransactionValidationException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Transaction precondition error",
                String.format(ex.getMessage()),
                HttpStatus.PRECONDITION_FAILED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.PRECONDITION_FAILED);
    }
}
