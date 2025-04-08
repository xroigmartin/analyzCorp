package xroigmartin.analyzcorp_backend.personal_economy.category.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.exceptions.CategoryValidationException;

import java.time.OffsetDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Category{
    private Long id;
    private String name;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;

    public static Category create(Long id, String name, String createdBy, String updatedBy){
        return Category.create(id, name, createdBy, updatedBy, OffsetDateTime.now(), OffsetDateTime.now());
    }

    public static Category create(Long id, String name, String createdBy, String updatedBy, OffsetDateTime createdAt, OffsetDateTime updatedAt){
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("Name of category is mandatory");
        }

        return new Category(id, name, createdAt, createdBy, updatedAt, updatedBy);
    }

    public void updateName(String newName){
        if(StringUtils.isBlank(newName)){
            throw new RuntimeException("Name of category is mandatory for create it.");
        }
        this.name = newName;
        updateUpdatedAt();
    }

    public void updateUpdatedBy(String newUpdated){
        if(StringUtils.isBlank(newUpdated)){
            throw new CategoryValidationException("User to update category is mandatory");
        }
        this.updatedBy = newUpdated;
        updateUpdatedAt();
    }

    private void updateUpdatedAt() {
        this.updatedAt = OffsetDateTime.now();
    }
}
