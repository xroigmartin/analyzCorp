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

    public static BudgetDTO convertBudgetToBudgetDto(Budget budget){

        var categoryDto = CategoryControllerUtils.convertCategoryToCategoryDTO(budget.category());

        return BudgetDTO.builder()
                .id(budget.id())
                .category(categoryDto)
                .amount(budget.amount())
                .startDate(budget.startDate())
                .endDate(budget.endDate())
                .createdAt(budget.createdAt())
                .createdBy(budget.createdBy())
                .updatedAt(budget.updatedAt())
                .updatedBy(budget.updatedBy())
                .build();
    }
}
