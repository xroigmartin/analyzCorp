package xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class ApiError implements Serializable {

    @Serial
    private static final long serialVersionUID = -5258655913798455840L;
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
}