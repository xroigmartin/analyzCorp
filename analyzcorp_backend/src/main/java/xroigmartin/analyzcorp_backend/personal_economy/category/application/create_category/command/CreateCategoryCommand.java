package xroigmartin.analyzcorp_backend.personal_economy.category.application.create_category.command;

public record CreateCategoryCommand(
        String name,
        String createdBy
) {
    public static CreateCategoryCommand createCategoryCommand(String name, String createdBy){
        return new CreateCategoryCommand(name, createdBy);
    }
}
