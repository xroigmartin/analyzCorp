package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;

@UtilityClass
public class CategoryJpaUtils {

    public static Category convertCategoryJpaToCategory(CategoryJpa categoryJpa){
        return Category.builder()
                .id(categoryJpa.getId())
                .name(categoryJpa.getName())
                .createdAt(categoryJpa.getCreatedAt())
                .createdBy(categoryJpa.getCreatedBy())
                .updatedAt(categoryJpa.getUpdatedAt())
                .updatedBy(categoryJpa.getUpdatedBy())
                .build();
    }

    public static CategoryJpa convertCategoryToCategoryJpa(Category category){
        return CategoryJpa.builder()
                .id(category.id())
                .name(category.name())
                .createdAt(category.createdAt())
                .createdBy(category.createdBy())
                .updatedAt(category.updatedAt())
                .updatedBy(category.updatedBy())
                .build();
    }
}
