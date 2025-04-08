package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.command.FindCategoryByKeywordCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

public interface FindCategoryByKeywordUseCase {

    Category handle(FindCategoryByKeywordCommand command);
}
