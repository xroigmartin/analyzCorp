package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.util.List;

public interface FindAllCategoriesUseCase {
    List<Category> handle();
}
