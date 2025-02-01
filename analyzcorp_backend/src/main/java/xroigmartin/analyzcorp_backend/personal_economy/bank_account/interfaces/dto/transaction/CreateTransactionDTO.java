package xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record CreateTransactionDTO (
        BigDecimal amount,
        String currency,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ssXXX"
        )
        OffsetDateTime date,
        TransactionType type,
        String description,
        Long accountId
){}
