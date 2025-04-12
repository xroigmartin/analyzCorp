package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.command;

public record FindCategoryByIdOrDefaultCommand(
        Long categoryId
) {
    public static FindCategoryByIdOrDefaultCommand create(Long categoryId){
        return new FindCategoryByIdOrDefaultCommand(categoryId);
    }
}
