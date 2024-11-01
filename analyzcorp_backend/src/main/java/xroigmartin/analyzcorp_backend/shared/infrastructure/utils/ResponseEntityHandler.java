package xroigmartin.analyzcorp_backend.shared.infrastructure.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;

@UtilityClass
public final class ResponseEntityHandler {

    public static <T> ResponseEntity<ApiResponse<T>> generate(ApiResponse<T> apiResponse, HttpStatus status){
        return new ResponseEntity<>(apiResponse, status);
    }
}
