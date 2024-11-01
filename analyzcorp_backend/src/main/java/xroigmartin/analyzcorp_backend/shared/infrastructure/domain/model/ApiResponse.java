package xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Builder
public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -834359869932740164L;

    private int statusCode;
    private String message;
    private String path;
    private T data;
    private ApiError error;
    private Instant timestamp;
}