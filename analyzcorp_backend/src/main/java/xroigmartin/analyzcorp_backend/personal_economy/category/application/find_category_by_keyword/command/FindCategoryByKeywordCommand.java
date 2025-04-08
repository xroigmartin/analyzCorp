package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.command;

public record FindCategoryByKeywordCommand(
        String keyword
) {
    public static FindCategoryByKeywordCommand create(String keyword){
        return new FindCategoryByKeywordCommand(keyword);
    }
}
