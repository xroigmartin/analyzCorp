package xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions;

import java.io.Serial;

public class CategoryValidationException extends CategoryDomainException{
    @Serial
    private static final long serialVersionUID = 6878943167425278566L;

    public CategoryValidationException(String message) {
        super(message);
    }
}
