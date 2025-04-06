package xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command;

import lombok.Builder;

@Builder
public record FindCategoryByIdCommand(
        Long id
) {}
