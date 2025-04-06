package xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions;

import java.io.Serial;

public abstract class CategoryDomainException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -8901299865772021671L;

    public CategoryDomainException(String message){
        super(message);
    }

    public CategoryDomainException(String message, Throwable cause){
        super(message, cause);
    }
}
