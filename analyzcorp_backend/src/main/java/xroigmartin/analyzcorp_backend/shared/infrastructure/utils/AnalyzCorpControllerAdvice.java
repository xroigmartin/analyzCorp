package xroigmartin.analyzcorp_backend.shared.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

}
