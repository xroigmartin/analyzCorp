package xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions.BudgetNotFoundExceptionBudget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions.BudgetDomainException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class BudgetControllerAdvice {

    @ExceptionHandler(BudgetDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleDomainException(BudgetDomainException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Bad request for budgets",
                String.format(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BudgetNotFoundExceptionBudget.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(BudgetNotFoundExceptionBudget ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Budget not found",
                String.format(ex.getMessage()),
                HttpStatus.NOT_FOUND.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.NOT_FOUND);
    }
}
