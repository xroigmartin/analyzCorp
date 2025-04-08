package xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.command.UpdateCategoryCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

public interface UpdateCategoryUseCase {

    Category handle(UpdateCategoryCommand command);
}
