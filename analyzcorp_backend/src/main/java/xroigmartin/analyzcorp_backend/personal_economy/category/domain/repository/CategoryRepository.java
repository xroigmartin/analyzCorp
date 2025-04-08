package xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.CategoryKeyword;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Long id);
    Category save(Category newCategory);
    Optional<Category> findCategoryByKeyword(String keyword);
    @Deprecated
    void addCategoryKeyword(CategoryKeyword categoryKeyword);


}
