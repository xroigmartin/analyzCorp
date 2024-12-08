package xroigmartin.analyzcorp_backend.shared.infrastructure.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;

@UtilityClass
public final class ResponseEntityHandler {

    public static <T> ResponseEntity<ApiResponse<T>> generate(ApiResponse<T> apiResponse, HttpStatus status){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-Encoding", "UTF-8");
        headers.setCacheControl("no-store");
        headers.set("X-Content-Type-Options", "nosniff");
        headers.set("X-Frame-Options", "DENY");

        return new ResponseEntity<>(apiResponse, headers, status);
    }
}
