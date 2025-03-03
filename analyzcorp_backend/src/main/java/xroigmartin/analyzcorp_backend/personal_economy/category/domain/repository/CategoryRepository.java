package xroigmartin.analyzcorp_backend.personal_economy.category.domain.repository;

import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAllCategories();
}
