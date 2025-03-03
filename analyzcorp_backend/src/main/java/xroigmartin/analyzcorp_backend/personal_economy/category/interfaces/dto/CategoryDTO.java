package xroigmartin.analyzcorp_backend.personal_economy.category.interfaces.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record CategoryDTO(
      Long id,
      String name,
      OffsetDateTime createdAt,
      String createdBy,
      OffsetDateTime updatedAt,
      String updatedBy) {}
