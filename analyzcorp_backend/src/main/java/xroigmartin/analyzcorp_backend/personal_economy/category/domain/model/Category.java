package xroigmartin.analyzcorp_backend.personal_economy.category.domain.model;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record Category (
        Long id,
        String name,
        OffsetDateTime createdAt,
        String createdBy,
        OffsetDateTime updatedAt,
        String updatedBy
){}
