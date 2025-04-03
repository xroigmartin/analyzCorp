package xroigmartin.analyzcorp_backend.shared.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.analyzcorp_backend.shared.domain.exceptions.NoFiltersProvidedException;
import xroigmartin.analyzcorp_backend.shared.domain.exceptions.PreconditionException;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;

@RestControllerAdvice
@Slf4j
public class AnalyzCorpControllerAdvice {

    @ExceptionHandler({UnsupportedOperationException.class,
                        HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiResponse<Void>> unsupportedMethodException(Exception ex){
        log.error("Unsupported operation: {}",ex.getMessage(), ex);
        var apiResponseError = ApiResponseHandler.generateError(
                "Method Not Allowed",
                "The requested method not allowed",
                HttpStatus.METHOD_NOT_ALLOWED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        log.error("An error occurred: {}", ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Internal Server Error",
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PreconditionException.class})
    public ResponseEntity<ApiResponse<Void>> handlePreconditionException(Exception ex) {
        log.warn("Precondition exception occurred: {}", ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Precondition required",
                "A precondition required occurred",
                HttpStatus.PRECONDITION_FAILED.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({NoFiltersProvidedException.class})
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(Exception ex) {
        log.warn("Bad request exception occurred: {}", ex.getMessage(), ex);

        var apiResponseError = ApiResponseHandler.generateError(
                "Bad request occurred",
                "A bad request occurred",
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntityHandler.generate(apiResponseError, HttpStatus.BAD_REQUEST);
    }

}
