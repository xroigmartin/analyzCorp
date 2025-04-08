package xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryDomainException;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryNotFoundException;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryValidationException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestControllerAdvice
@Slf4j
public class CategoryControllerAdvice {

    @ExceptionHandler(CategoryDomainException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryDomainException(CategoryDomainException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Bad request for category",
                String.format(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Category not found",
                String.format(ex.getMessage()),
                HttpStatus.NOT_FOUND.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoryValidationException(CategoryValidationException ex) {
        log.error(ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Category precondition error",
                String.format(ex.getMessage()),
                HttpStatus.PRECONDITION_FAILED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.PRECONDITION_FAILED);
    }
}
