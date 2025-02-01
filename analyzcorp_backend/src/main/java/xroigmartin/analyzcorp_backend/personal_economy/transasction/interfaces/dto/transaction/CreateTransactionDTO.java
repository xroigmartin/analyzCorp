package xroigmartin.analyzcorp_backend.personal_economy.transasction.interfaces.dto.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.enums.TransactionType;

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
