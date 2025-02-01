package xroigmartin.analyzcorp_backend.personal_economy.transasction.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.exceptions.CreateTransactionException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class TransactionControllerAdvice {

    @ExceptionHandler({CreateTransactionException.class})
    public ResponseEntity<ApiResponse<Void>> TransactionException(Exception ex){
        log.error("Create transaction error: {}",ex.getMessage(), ex);
        var apiResponseError = ApiResponseHandler.generateError(
                "Create transaction error",
                String.format("Create transaction error: %s",ex.getMessage()),
                HttpStatus.METHOD_NOT_ALLOWED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
