package xroigmartin.analyzcorp_backend.shared.infrastructure.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiError;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;

import java.time.Instant;

@UtilityClass
public final class ApiResponseHandler {

    public static <T> ApiResponse<T> generateSuccess(T data, String infoMessage, int statusCode){
        String path = getCurrentRequestUri();

        return ApiResponse.<T>builder()
                .statusCode(statusCode)
                .message(infoMessage)
                .path(path)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public ApiResponse<Void> generateError(String errorTitle, String errorDetail, int statusCode){
        String path = getCurrentRequestUri();

        var apiError = ApiError.builder()
                .type("about:blank")
                .title(errorTitle)
                .status(statusCode)
                .detail(errorDetail)
                .instance(path)
                .build();

        return ApiResponse.<Void>builder()
                .statusCode(statusCode)
                .message(errorTitle)
                .path(path)
                .data(null)
                .error(apiError)
                .timestamp(Instant.now())
                .build();
    }

    private static String getCurrentRequestUri() {
        try{
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
        }
        catch (IllegalStateException | NullPointerException ex){
            return "/uknown";
        }
    }
}
