package xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.exceptions;

import java.io.Serial;

public class CreateTransactionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5831609292587307508L;

    public CreateTransactionException(String message) {
        super(message);
    }
}
