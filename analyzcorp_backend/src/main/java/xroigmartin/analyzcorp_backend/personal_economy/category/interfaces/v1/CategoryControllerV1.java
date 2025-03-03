package xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.CategoryService;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.CategoryDTO;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.CATEGORY_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.SUCCESS_FIND_ALL_CATEGORIES;


@RestController
@RequestMapping(CATEGORY_PATH)
@AllArgsConstructor
public class CategoryControllerV1 {

    private final CategoryService categoryService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> findAllCategories(){
        var categories = this.categoryService.findCategories();

        var categoriesDTOs = categories.stream()
                .map(CategoryControllerUtils::convertCategoryToCategoryDTO)
                .toList();

        var apiResponse = ApiResponseHandler.generateSuccess(categoriesDTOs, SUCCESS_FIND_ALL_CATEGORIES, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
