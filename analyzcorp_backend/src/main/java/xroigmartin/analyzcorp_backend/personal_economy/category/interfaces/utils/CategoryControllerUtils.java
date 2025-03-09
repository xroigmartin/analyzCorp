package xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils;

import lombok.experimental.UtilityClass;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.CategoryDTO;

import static xroigmartin.analyzcorp_backend.personal_economy.shared.interfaces.utils.PersonalEconomyControllerConstants.PERSONAL_ECONOMY_PATH;

@UtilityClass
public class CategoryControllerUtils {

    public static final String CATEGORY_PATH = PERSONAL_ECONOMY_PATH + "/categories";
    public static final String SUCCESS_FIND_ALL_CATEGORIES = "Find all categories";
    public static final String SUCCESS_CREATE_CATEGORY = "Create new category";
    public static final String SUCCESS_GET_CATEGORY = "Get category";
    public static final String SUCCESS_UPDATE_CATEGORY = "Update category";

    public CategoryDTO convertCategoryToCategoryDTO(Category category){
        return CategoryDTO.builder()
                .id(category.id())
                .name(category.name())
                .createdAt(category.createdAt())
                .createdBy(category.createdBy())
                .updatedAt(category.updatedAt())
                .updatedBy(category.updatedBy())
                .build();
    }
}
