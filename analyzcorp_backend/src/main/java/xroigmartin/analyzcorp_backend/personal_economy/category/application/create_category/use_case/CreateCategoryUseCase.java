package xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.command.CreateCategoryCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

public interface CreateCategoryUseCase {

    Category handle(CreateCategoryCommand command);
}
