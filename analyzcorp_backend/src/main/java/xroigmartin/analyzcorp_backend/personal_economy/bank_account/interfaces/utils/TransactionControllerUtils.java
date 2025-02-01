package xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.dto.transaction.TransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Transaction;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

@UtilityClass
public class TransactionControllerUtils {

    public static final String TRANSACTION_PATH = PERSONAL_ECONOMY_PATH + "/transactions";
    public static final String SUCCESS_CREATE_TRANSACTION = "Created transaction";

    public static TransactionDTO convertToTransactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.id())
                .amount(transaction.amount())
                .currency(transaction.currency())
                .date(transaction.date())
                .type(transaction.type())
                .description(transaction.description())
                .accountId(transaction.accountId())
                .createdAt(transaction.createdAt())
                .createdBy(transaction.createdBy())
                .updatedAt(transaction.updatedAt())
                .updatedBy(transaction.updatedBy())
                .build();
    }
}
