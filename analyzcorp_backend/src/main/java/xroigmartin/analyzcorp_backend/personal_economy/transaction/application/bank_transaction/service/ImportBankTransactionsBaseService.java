package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.service;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.command.FindCategoryByKeywordCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case.FindCategoryByKeywordUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.util.Optional;

@AllArgsConstructor
public abstract class ImportBankTransactionsBaseService {

    protected final FindAllCategoriesUseCase findAllCategoriesUseCase;
    protected final FindCategoryByKeywordUseCase findCategoryByKeywordUseCase;

    protected Category getCategoryByKeyword(String keyword){
        return Optional.ofNullable(keyword)
                .filter(StringUtils::isNotBlank)
                .map(key -> findCategoryByKeywordUseCase.handle(FindCategoryByKeywordCommand.create(key)))
                .orElseGet(this::getVariousCategory);
    }

    private Category getVariousCategory(){
        var categories = findAllCategoriesUseCase.handle();

        return categories.stream()
                .filter(cat -> cat.getName().equals("Gastos Varios"))
                .findFirst()
                .get();
    }
}
