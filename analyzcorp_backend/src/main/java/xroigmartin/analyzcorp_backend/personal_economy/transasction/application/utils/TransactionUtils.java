package xroigmartin.analyzcorp_backend.personal_economy.transasction.application.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.interfaces.dto.transaction.CreateTransactionDTO;

@UtilityClass
public class TransactionUtils {

    public static Transaction convertCreateTransactionToTransaction(CreateTransactionDTO createTransaction) {

        var dateTransaction = createTransaction.date().toInstant().atOffset(ZoneOffset.UTC);

        return Transaction.builder()
                .amount(createTransaction.amount())
                .currency(createTransaction.currency())
                .date(dateTransaction)
                .type(createTransaction.type())
                .description(createTransaction.description())
                .accountId(createTransaction.accountId())
                .createdAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                .createdBy("SYSTEM")
                .updatedAt(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.UTC))
                .updatedBy("SYSTEM")
                .build();
    }
}
