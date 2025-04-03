package xroigmartin.analyzcorp_backend.shared.domain.exceptions;

import java.io.Serial;

public class PreconditionException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -4426973537761751337L;

    public PreconditionException(String message) {
        super(message);
    }
}
