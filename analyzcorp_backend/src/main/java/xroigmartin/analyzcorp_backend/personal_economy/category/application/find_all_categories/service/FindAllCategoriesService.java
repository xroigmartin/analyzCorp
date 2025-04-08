package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllCategoriesService implements FindAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> handle() {
        return categoryRepository.findAllCategories();
    }
}
