package xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record AccountDTO(
        Long id,
        String bankName,
        String iban,
        String alias,
        String createdBy,
        OffsetDateTime createdAt,
        String updatedBy,
        OffsetDateTime updatedAt
) {

    public static AccountDTO create(Long id, String bankName, String iban, String alias, String createdBy, OffsetDateTime createdAt, String updatedBy, OffsetDateTime updatedAt){
        return AccountDTO.builder()
                .id(id)
                .bankName(bankName)
                .iban(iban)
                .alias(alias)
                .createdBy(createdBy)
                .createdAt(createdAt)
                .updatedBy(updatedBy)
                .updatedAt(updatedAt)
                .build();
    }
}
