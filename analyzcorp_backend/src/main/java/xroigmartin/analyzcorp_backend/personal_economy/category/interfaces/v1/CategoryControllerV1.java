package xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.v1;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.command.CreateCategoryCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.use_case.CreateCategoryUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case.FindCategoryByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.command.UpdateCategoryCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.use_case.UpdateCategoryUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.CategoryDTO;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.CreateCategoryDTO;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto.UpdateCategoryDTO;
import xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.CATEGORY_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.SUCCESS_CREATE_CATEGORY;
import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.SUCCESS_FIND_ALL_CATEGORIES;
import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.SUCCESS_GET_CATEGORY;
import static xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.utils.CategoryControllerUtils.SUCCESS_UPDATE_CATEGORY;


@RestController
@RequestMapping(CATEGORY_PATH)
@AllArgsConstructor
public class CategoryControllerV1 {

    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> findAllCategories(){
        var categories = findAllCategoriesUseCase.handle();

        var categoriesDTOs = categories.stream()
                .map(CategoryControllerUtils::convertCategoryToCategoryDTO)
                .toList();

        var apiResponse = ApiResponseHandler.generateSuccess(categoriesDTOs, SUCCESS_FIND_ALL_CATEGORIES, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        var command = CreateCategoryCommand.createCategoryCommand(createCategoryDTO.name(), createCategoryDTO.createdBy());
        var category = createCategoryUseCase.handle(command);

        var categoryDTO = CategoryControllerUtils.convertCategoryToCategoryDTO(category);

        var apiResponse = ApiResponseHandler.generateSuccess(categoryDTO, SUCCESS_CREATE_CATEGORY, HttpStatus.CREATED.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(value="{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable Long categoryId){
        var command = FindCategoryByIdCommand.create(categoryId);
        var category = findCategoryByIdUseCase.handle(command);

        var categoryDTO = CategoryControllerUtils.convertCategoryToCategoryDTO(category);

        var apiResponse = ApiResponseHandler.generateSuccess(categoryDTO, SUCCESS_GET_CATEGORY, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @PutMapping(value="/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategoryDTO updateCategoryDTO){
        var command = UpdateCategoryCommand.create(categoryId, updateCategoryDTO.name(), updateCategoryDTO.updatedBy());
        var category = updateCategoryUseCase.handle(command);

        var categoryDTO = CategoryControllerUtils.convertCategoryToCategoryDTO(category);

        var apiResponse = ApiResponseHandler.generateSuccess(categoryDTO, SUCCESS_UPDATE_CATEGORY, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
