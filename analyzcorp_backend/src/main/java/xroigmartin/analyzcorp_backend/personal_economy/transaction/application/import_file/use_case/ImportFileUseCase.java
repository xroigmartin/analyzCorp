package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.use_case;

import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.command.ImportFileCommand;

import java.io.IOException;

public interface ImportFileUseCase {

    void handle(ImportFileCommand command) throws IOException;
}
