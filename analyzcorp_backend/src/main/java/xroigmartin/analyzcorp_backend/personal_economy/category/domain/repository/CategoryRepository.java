package xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.CategoryKeyword;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAllCategories();

    Category createCategory(Category newCategory);

    Optional<Category> getCategoryId(Long categoryId);

    Category updateCategory(Category updateCategory);

    Category findCategoryByDescription(String description);

    void addCategoryKeyword(CategoryKeyword categoryKeyword);
}
