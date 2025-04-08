package xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions;

import java.io.Serial;

public class CategoryNotFoundException extends CategoryDomainException{

    @Serial
    private static final long serialVersionUID = -6595297168872979567L;

    public CategoryNotFoundException(Long id) {
        super(String.format("Not found category with id %d", id));
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
