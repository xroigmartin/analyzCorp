package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

public interface FindCategoryByIdUseCase {
    Category handle(FindCategoryByIdCommand command);
}
