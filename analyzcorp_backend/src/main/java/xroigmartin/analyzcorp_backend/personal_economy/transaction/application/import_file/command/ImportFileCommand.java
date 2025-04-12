package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.command;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;

public record ImportFileCommand(
        Long accountId,
        FileImportType fileImportType,
        MultipartFile file,
        String createdBy
) {
    public static ImportFileCommand create(Long accountId, FileImportType fileImportType, MultipartFile file, String createdBy) {
        return new ImportFileCommand(accountId, fileImportType, file, createdBy);
    }
}
