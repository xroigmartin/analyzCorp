package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.CategoryService;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.util.Optional;

@AllArgsConstructor
public abstract class BankTransactionBaseService {

    protected final CategoryService categoryService;

    protected Category getCategoryByDescription(String description){
        return Optional.ofNullable(description)
                .filter(StringUtils::isNotBlank)
                .map(categoryService::findCategoryByDescription)
                .orElseGet(this::getVariousCategory);
    }

    private Category getVariousCategory(){
        var categories = this.categoryService.findCategories();

        return categories.stream()
                .filter(cat -> cat.name().equals("Gastos Varios"))
                .findFirst()
                .get();
    }
}
