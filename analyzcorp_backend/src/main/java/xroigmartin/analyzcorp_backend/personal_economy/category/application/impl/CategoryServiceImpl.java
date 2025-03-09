package xroigmartin.analyzcorp_backend.personal_economy.category.application.impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.CategoryService;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategories() {
        return this.categoryRepository.findAllCategories();
    }

    @Override
    public Category createCategory(String name) {
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("Name of category is mandatory for create it.");
        }

        var newCategory = Category.builder()
                .name(name)
                .createdBy("SYSTEM")
                .createdAt(OffsetDateTime.now())
                .updatedBy("SYSTEM")
                .updatedAt(OffsetDateTime.now())
                .build();

        return this.categoryRepository.createCategory(newCategory);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        if(categoryId == null){
            throw new RuntimeException("Category id of category is mandatory for search a category.");
        }

        var OptionalCategory = this.categoryRepository.getCategoryId(categoryId);

        return OptionalCategory.orElseThrow(() -> new RuntimeException("Category not found."));
    }

    @Override
    public Category updateCategory(Long categoryId, String name) {
        if(categoryId == null){
            throw new RuntimeException("Category id of category is mandatory for update a category.");
        }

        if(StringUtils.isBlank(name)){
            throw new RuntimeException("Name of category is mandatory for update a category.");
        }

        var optionalCategory = this.categoryRepository.getCategoryId(categoryId);

        var category = optionalCategory.get();

        var updateCategory = Category.builder()
                .id(category.id())
                .name(name)
                .updatedBy("SYSTEM")
                .updatedAt(OffsetDateTime.now())
                .createdBy(category.createdBy())
                .createdAt(category.createdAt())
                .build();

        return this.categoryRepository.updateCategory(updateCategory);
    }
}
