package xroigmartin.analyzcorp_backend.shared.domain.exceptions;

import java.io.Serial;

public class NoFiltersProvidedException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 8678719757301506869L;

    public NoFiltersProvidedException(String message) {
        super(message);
    }
}
