package xroigmartin.analyzcorp_backend.personal_economy.category.application.update_category.command;

public record UpdateCategoryCommand(
        Long id,
        String name,
        String updatedBy
) {
    public static UpdateCategoryCommand create(Long id, String name, String updatedBy){
        return new UpdateCategoryCommand(id, name, updatedBy);
    }
}
