package xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.CategoryKeyword;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    @Deprecated
    List<Category> findAllCategories();

    @Deprecated
    Category createCategory(Category newCategory);

    @Deprecated
    Optional<Category> getCategoryId(Long categoryId);

    @Deprecated
    Category updateCategory(Category updateCategory);

    @Deprecated
    Category findCategoryByDescription(String description);

    @Deprecated
    void addCategoryKeyword(CategoryKeyword categoryKeyword);

    Optional<Category> findCategoryById(Long id);
}
