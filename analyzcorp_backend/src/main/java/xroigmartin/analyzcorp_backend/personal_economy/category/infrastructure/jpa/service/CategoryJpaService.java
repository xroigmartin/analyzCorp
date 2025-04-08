package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.service;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.CategoryKeyword;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository.CategoryRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository.CategoryJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository.CategoryKeywordJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.utils.CategoryJpaUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryJpaService implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryKeywordJpaRepository categoryKeywordJpaRepository;

    @Override
    public List<Category> findAllCategories() {
        var categoriesJpa = this.categoryJpaRepository.findAll();

        return categoriesJpa.stream()
                .map(CategoryJpaUtils::toDomain)
                .toList();
    }

    @Override
    public Category save(Category newCategory) {
        var saved = categoryJpaRepository.save(CategoryJpaUtils.toEntity(newCategory));
        return CategoryJpaUtils.toDomain(saved);
    }

    @Override
    public Optional<Category> findCategoryByKeyword(String keyword) {
        if(StringUtils.isBlank(keyword)){
            return Optional.empty();
        }

        return categoryKeywordJpaRepository.findByKeyword(keyword)
                .map(categoryKeywordJpa -> CategoryJpaUtils.toDomain(categoryKeywordJpa.getCategory()));
    }


    @Override
    public void addCategoryKeyword(CategoryKeyword categoryKeyword) {
        if(categoryKeyword == null){
            return;
        }

        var categoryKeywordJpa = CategoryJpaUtils.convertCategoryKeywordToCategoryKeywordJpa(categoryKeyword);
        categoryKeywordJpaRepository.save(categoryKeywordJpa);
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryJpaRepository.findById(id)
                .map(CategoryJpaUtils::toDomain);
    }



}
