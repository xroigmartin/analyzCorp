package xroigmartin.analyzcorp_backend.personal_economy.budget.application.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.services.BudgetService;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository.BudgetRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.CategoryService;
import xroigmartin.analyzcorp_backend.shared.domain.exceptions.PreconditionException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryService categoryService;

    @Override
    public List<Budget> getAllBudgets(Integer year) {
        if(year == null || year <= 0){
            throw new PreconditionException("Year is mandatory for search budget");
        }

        var budgets = budgetRepository.getBudgets(year);

        if(CollectionUtils.isEmpty(budgets)){
            budgets = createBudgetForYear(year);
        }

        return budgets;
    }

    private List<Budget> createBudgetForYear(Integer year) {
        var categories = categoryService.findCategories();
        var budgets = new ArrayList<Budget>();

        for(int month = 1; month <= 12; month++){
            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

            categories.forEach(cat ->{
                var budget = Budget.builder()
                        .category(cat)
                        .amount(BigDecimal.ZERO)
                        .startDate(startDate)
                        .endDate(endDate)
                        .createdAt(OffsetDateTime.now())
                        .createdBy("SYSTEM")
                        .updatedAt(OffsetDateTime.now())
                        .updatedBy("SYSTEM")
                        .build();

                budgets.add(budget);
            });
        }

       return budgetRepository.saveBudgets(budgets);
    }
}
