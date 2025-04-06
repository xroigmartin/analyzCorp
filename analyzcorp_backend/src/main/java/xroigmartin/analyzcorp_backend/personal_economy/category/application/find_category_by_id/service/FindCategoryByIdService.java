package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case.FindCategoryByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryNotFoundException;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class FindCategoryByIdService implements FindCategoryByIdUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category handle(FindCategoryByIdCommand command) {
        return categoryRepository.findCategoryById(command.id())
                .orElseThrow(() -> new CategoryNotFoundException(command.id()));
    }
}
