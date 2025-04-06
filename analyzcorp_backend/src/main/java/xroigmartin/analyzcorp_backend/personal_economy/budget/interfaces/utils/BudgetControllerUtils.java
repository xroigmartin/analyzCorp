package xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.dto.BudgetDTO;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

@UtilityClass
public class BudgetControllerUtils {

    public static final String BUDGET_PATH = PERSONAL_ECONOMY_PATH + "/budgets";
    public static final String SUCCESS_GET_BUDGETS = "Get budgets";
    public static final String SUCCESS_BUDGETS_AMOUNT_UPDATE = "Update budget amount";

    public static BudgetDTO convertBudgetToBudgetDto(Budget budget){

        var categoryDto = CategoryControllerUtils.convertCategoryToCategoryDTO(budget.getCategory());

        return BudgetDTO.builder()
                .id(budget.getId())
                .category(categoryDto)
                .amount(budget.getAmount())
                .startDate(budget.getStartDate())
                .endDate(budget.getEndDate())
                .createdAt(budget.getCreatedAt())
                .createdBy(budget.getCreatedBy())
                .updatedAt(budget.getUpdatedAt())
                .updatedBy(budget.getUpdatedBy())
                .build();
    }
}
