package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions;

import java.io.Serial;

public class TransactionNotFoundByIdException extends TransactionDomainException{

    @Serial
    private static final long serialVersionUID = 5202337024472422285L;

    public TransactionNotFoundByIdException(Long id) {
        super(String.format("Transaction with id %s not found", id));
    }
}
