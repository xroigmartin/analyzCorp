package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.command.FindCategoryByIdOrDefaultCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

public interface FindCategoryByIdOrDefaultUseCase {

    Category handle(FindCategoryByIdOrDefaultCommand command);
}
