package xroigmartin.analyzcorp_backend.personal_economy.shared.domain.exceptions;

import java.io.Serial;

public class AmountValueObjectException extends DomainException{
    @Serial
    private static final long serialVersionUID = 5436033398819299138L;

    public AmountValueObjectException(String message) {
        super(message);
    }
}
