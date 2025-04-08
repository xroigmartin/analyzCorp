package xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.command.UpdateCategoryCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.use_case.UpdateCategoryUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryNotFoundException;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class UpdateCategoryService implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category handle(UpdateCategoryCommand command) {
        var category = categoryRepository.findCategoryById(command.id())
                .orElseThrow(() -> new CategoryNotFoundException(command.id()));

        category.updateName(command.name());
        category.updateUpdatedBy(command.updatedBy());

        return categoryRepository.save(category);
    }
}
