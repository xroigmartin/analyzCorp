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

        var categoryDTO = CategoryControllerUtils.convertCategoryToCategoryDTO(transaction.category());

        return TransactionDTO.builder()
                .id(transaction.id())
                .amount(transaction.amount())
                .currency(transaction.currency())
                .date(transaction.date())
                .type(transaction.type())
                .description(transaction.description())
                .category(categoryDTO)
                .accountId(transaction.accountId())
                .createdAt(transaction.createdAt())
                .createdBy(transaction.createdBy())
                .updatedAt(transaction.updatedAt())
                .updatedBy(transaction.updatedBy())
                .build();
    }
}
