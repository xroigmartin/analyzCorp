package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository.CategoryJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Category> getCategoryId(Long categoryId) {

        var optionalCategoryJpa = this.categoryJpaRepository.findById(categoryId);

        if (optionalCategoryJpa.isPresent()) {
            var categoryJpa = optionalCategoryJpa.get();
            return Optional.of(CategoryJpaUtils.convertCategoryJpaToCategory(categoryJpa));
        }

        return Optional.empty();
    }

    @Override
    public Category updateCategory(Category updateCategory) {

        var optionalCategoryJpa = this.categoryJpaRepository.findById(updateCategory.id());

        if(optionalCategoryJpa.isPresent()) {
            var categoryJpa = optionalCategoryJpa.get();
            categoryJpa.setName(updateCategory.name());
            categoryJpa.setUpdatedAt(updateCategory.updatedAt());
            categoryJpa.setUpdatedBy(updateCategory.updatedBy());
            var categoryUpdated = this.categoryJpaRepository.save(categoryJpa);
            return CategoryJpaUtils.convertCategoryJpaToCategory(categoryUpdated);
        }

        return null;
    }
}
