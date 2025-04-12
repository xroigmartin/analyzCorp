package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;

public record ImportTransactionsDTO(
        Long accountId,
        FileImportType fileImportType,
        String createdBy
) {
}
