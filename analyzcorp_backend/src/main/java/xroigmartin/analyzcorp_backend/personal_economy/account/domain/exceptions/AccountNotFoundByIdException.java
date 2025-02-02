package xroigmartin.analyzcorp_backend.personal_economy.account.domain.exceptions;

import java.io.Serial;

public class AccountNotFoundByIdException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6694314933677693607L;

    public AccountNotFoundByIdException(long id) {
        super(String.format("Not exists bank account with id %d",id));
    }
}
