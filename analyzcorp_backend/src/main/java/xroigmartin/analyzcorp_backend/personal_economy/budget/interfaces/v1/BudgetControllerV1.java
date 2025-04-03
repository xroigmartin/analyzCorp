package xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.services.BudgetService;
import xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.dto.BudgetDTO;
import xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.utils.BudgetControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.utils.BudgetControllerUtils.BUDGET_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.budget.interfaces.utils.BudgetControllerUtils.SUCCESS_GET_BUDGETS;


@RestController
@RequestMapping(BUDGET_PATH)
@AllArgsConstructor
public class BudgetControllerV1 {

    private final BudgetService budgetService;

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<BudgetDTO>>> getAllBudgets(@RequestParam(required = false) Integer year) {

        var budgets = budgetService.getAllBudgets(year);

        var budgetsDto = budgets.parallelStream().map(BudgetControllerUtils::convertBudgetToBudgetDto).toList();

        var apiResponse = ApiResponseHandler.generateSuccess(budgetsDto, SUCCESS_GET_BUDGETS, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
