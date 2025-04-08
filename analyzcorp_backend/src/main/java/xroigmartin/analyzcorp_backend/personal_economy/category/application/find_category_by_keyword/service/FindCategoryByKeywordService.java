package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.service;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.command.FindCategoryByKeywordCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case.FindCategoryByKeywordUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryNotFoundException;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class FindCategoryByKeywordService implements FindCategoryByKeywordUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category handle(FindCategoryByKeywordCommand command) {
        if(StringUtils.isBlank(command.keyword())){
            return null;
        }

        return this.categoryRepository.findCategoryByKeyword(command.keyword())
                .orElseThrow(() -> new CategoryNotFoundException(String.format("Not found category with keyword %s", command.keyword())));
    }
}
