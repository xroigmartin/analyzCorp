package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.CategoryKeyword;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryKeywordJpa;

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

    public static CategoryKeywordJpa convertCategoryKeywordToCategoryKeywordJpa(CategoryKeyword categoryKeyword){

        var category = convertCategoryToCategoryJpa(categoryKeyword.category());

        return CategoryKeywordJpa.builder()
                .id(categoryKeyword.id())
                .keyword(categoryKeyword.keyword())
                .category(category)
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .updatedAt(category.getUpdatedAt())
                .updatedBy(category.getUpdatedBy())
                .build();
    }
}
