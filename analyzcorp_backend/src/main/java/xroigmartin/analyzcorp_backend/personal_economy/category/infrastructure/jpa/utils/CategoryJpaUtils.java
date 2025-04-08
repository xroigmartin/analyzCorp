package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.CategoryKeyword;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryKeywordJpa;

@UtilityClass
public class CategoryJpaUtils {

    public static Category toDomain(CategoryJpa categoryJpa){
        return Category.create(categoryJpa.getId(), categoryJpa.getName(), categoryJpa.getCreatedBy(),
                 categoryJpa.getUpdatedBy(), categoryJpa.getCreatedAt(), categoryJpa.getUpdatedAt());
    }

    public static CategoryJpa toEntity(Category category){
        return CategoryJpa.builder()
                .id(category.getId())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .updatedAt(category.getUpdatedAt())
                .updatedBy(category.getUpdatedBy())
                .build();
    }

    public static CategoryKeywordJpa convertCategoryKeywordToCategoryKeywordJpa(CategoryKeyword categoryKeyword){

        var category = toEntity(categoryKeyword.category());

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
