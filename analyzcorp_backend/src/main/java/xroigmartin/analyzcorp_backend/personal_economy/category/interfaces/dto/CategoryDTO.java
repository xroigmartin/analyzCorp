package xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto;

import java.time.OffsetDateTime;

public record CategoryDTO(
      Long id,
      String name,
      OffsetDateTime createdAt,
      String createdBy,
      OffsetDateTime updatedAt,
      String updatedBy) {
    public static CategoryDTO create(Long id, String name, OffsetDateTime createdAt, String createdBy, OffsetDateTime updatedAt, String updatedBy){
        return new CategoryDTO(id, name, createdAt, createdBy, updatedAt, updatedBy);
    }
}
