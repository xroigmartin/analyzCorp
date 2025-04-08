package xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.command.CreateBudgetCommand;
import xroigmartin.analyzcorp_backend.personal_economy.budget.application.create_budget.use_case.CreateBudgetUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model.Budget;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.repository.BudgetRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case.FindCategoryByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

@Service
@AllArgsConstructor
public class CreateBudgetService implements CreateBudgetUseCase {

    private final BudgetRepository budgetRepository;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;

    @Override
    public Budget handle(CreateBudgetCommand createBudgetCommand) {

        var category = getCategory(createBudgetCommand.categoryId());
        var account = getAccount(createBudgetCommand.accountId());

        var budget = Budget.create(category, account, createBudgetCommand.amount(), createBudgetCommand.startDate(), createBudgetCommand.endDate(), createBudgetCommand.createdBy(), createBudgetCommand.createdBy());

        return budgetRepository.save(budget);
    }

    private Category getCategory(Long categoryId){
        var findCategoryByIdCommand = FindCategoryByIdCommand.create(categoryId);
        return findCategoryByIdUseCase.handle(findCategoryByIdCommand);
    }

    private Account getAccount(Long accountId){
        var command = FindAccountByIdCommand.create(accountId);

        return findAccountByIdUseCase.handle(command);
    }
}
