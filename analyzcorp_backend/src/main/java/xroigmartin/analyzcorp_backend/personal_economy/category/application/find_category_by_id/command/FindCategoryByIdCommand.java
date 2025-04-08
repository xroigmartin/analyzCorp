package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command;

public record FindCategoryByIdCommand(
        Long id
) {
    public static FindCategoryByIdCommand create(Long id){
        return new FindCategoryByIdCommand(id);
    }
}
