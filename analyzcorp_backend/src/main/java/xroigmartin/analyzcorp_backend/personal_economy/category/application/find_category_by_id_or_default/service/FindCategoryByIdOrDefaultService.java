package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case.FindCategoryByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.command.FindCategoryByIdOrDefaultCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.use_case.FindCategoryByIdOrDefaultUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.TransactionValidationException;

@Service
@AllArgsConstructor
public class FindCategoryByIdOrDefaultService implements FindCategoryByIdOrDefaultUseCase {

    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;

    @Override
    public Category handle(FindCategoryByIdOrDefaultCommand command) {
        if(command.categoryId() != null){
            return findCategoryByIdUseCase.handle(FindCategoryByIdCommand.create(command.categoryId()));
        }

        return findAllCategoriesUseCase.handle().stream()
                .filter(cat -> "Gastos Varios".equals(cat.getName()))
                .findFirst()
                .orElseThrow(() -> new TransactionValidationException("Default category not found"));
    }
}
