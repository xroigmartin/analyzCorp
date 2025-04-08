package xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.command.CreateCategoryCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.use_case.CreateCategoryUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CreateCategoryService implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public Category handle(CreateCategoryCommand command) {
        var category = Category.create(null, command.name(), command.createdBy(), command.createdBy());
        return this.categoryRepository.save(category);
    }
}
