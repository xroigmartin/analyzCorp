package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository.CategoryJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryJpaService implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public List<Category> findAllCategories() {
        var categoriesJpa = this.categoryJpaRepository.findAll();

        return categoriesJpa.stream()
                .map(CategoryJpaUtils::convertCategoryJpaToCategory)
                .toList();
    }

    @Override
    public Category createCategory(Category newCategory) {
        var newCategoryJpa = CategoryJpaUtils.convertCategoryToCategoryJpa(newCategory);

        var categoryJpa = this.categoryJpaRepository.save(newCategoryJpa);

        return CategoryJpaUtils.convertCategoryJpaToCategory(categoryJpa);
    }
}
