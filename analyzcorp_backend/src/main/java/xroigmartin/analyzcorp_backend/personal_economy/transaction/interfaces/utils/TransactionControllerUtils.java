package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.TransactionDTO;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

@UtilityClass
public class TransactionControllerUtils {

    public static final String TRANSACTION_PATH = PERSONAL_ECONOMY_PATH + "/transactions";
    public static final String SUCCESS_CREATE_TRANSACTION = "Created transaction";
    public static final String SUCCESS_UPDATE_TRANSACTION = "Updated transaction";
    public static final String SUCCESS_FIND_TRANSACTIONS_BY_ACCOUNT_ID = "Find transactions by account id";

    public static TransactionDTO convertToTransactionDTO(Transaction transaction) {

        var categoryDTO = CategoryControllerUtils.convertCategoryToCategoryDTO(transaction.getCategory());

        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount().value())
                .currency(transaction.getCurrency().code())
                .date(transaction.getDate())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .category(categoryDTO)
                .accountId(transaction.getAccount().getId())
                .createdAt(transaction.getCreatedAt())
                .createdBy(transaction.getCreatedBy())
                .updatedAt(transaction.getUpdatedAt())
                .updatedBy(transaction.getUpdatedBy())
                .build();
    }
}
