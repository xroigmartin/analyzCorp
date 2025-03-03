package xroigmartin.analyzcorp_backend.personal_economy.category.application;

import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findCategories();
}
