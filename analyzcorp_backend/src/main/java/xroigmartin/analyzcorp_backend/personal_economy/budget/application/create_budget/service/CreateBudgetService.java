package xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.command.CreateBudgetCommand;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.use_case.CreateBudgetUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions.BudgetValidationException;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository.BudgetRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case.FindCategoryByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CreateBudgetService implements CreateBudgetUseCase {

    private final BudgetRepository budgetRepository;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;

    @Override
    public Budget handle(CreateBudgetCommand createBudgetCommand) {
        if(createBudgetCommand.amount() == null || createBudgetCommand.amount().signum() < 0){
            throw new BudgetValidationException("Amount must be non-negative");
        }

        if(createBudgetCommand.startDate() == null || createBudgetCommand.endDate() == null
            || createBudgetCommand.startDate().isAfter(createBudgetCommand.endDate())){
            throw new BudgetValidationException("Start date must be before end date of budget");
        }

        var category = getCategory(createBudgetCommand.categoryId());
        var account = getAccount(createBudgetCommand.accountId());
        var now = OffsetDateTime.now();

        var budget = Budget.builder()
                .category(category)
                .account(account)
                .amount(createBudgetCommand.amount())
                .startDate(createBudgetCommand.startDate())
                .endDate(createBudgetCommand.endDate())
                .createdAt(now)
                .updatedAt(now)
                .createdBy(createBudgetCommand.createdBy())
                .updatedBy(createBudgetCommand.createdBy())
                .build();

        return budgetRepository.save(budget);
    }

    private Category getCategory(Long categoryId){
        var findCategoryByIdCommand = FindCategoryByIdCommand.builder()
                .id(categoryId)
                .build();

        return findCategoryByIdUseCase.handle(findCategoryByIdCommand);
    }

    private Account getAccount(Long accountId){
        var command = FindAccountByIdCommand.builder()
                .id(accountId)
                .build();

        return findAccountByIdUseCase.handle(command);
    }
}
