package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.v1;

import static xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils.SUCCESS_CREATE_TRANSACTION;
import static xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils.TRANSACTION_PATH;

import java.io.IOException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.TransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.TransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

@RestController
@RequestMapping(TRANSACTION_PATH)
@AllArgsConstructor
public class TransactionControllerV1 {

    private final TransactionService transactionService;

    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<TransactionDTO>> createTransaction(@RequestBody @Valid CreateTransactionDTO createTransactionDTO) {
        var transaction = transactionService.createTransaction(createTransactionDTO);

        var transactionDTO = TransactionControllerUtils.convertToTransactionDTO(transaction);

        var apiResponse = ApiResponseHandler.generateSuccess(transactionDTO, SUCCESS_CREATE_TRANSACTION, HttpStatus.CREATED.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping(value="/import_file")
    public ResponseEntity<ApiResponse<String>> importFile(@RequestParam("accountId") Long accountId,
                                                          @RequestParam("fileImportType") FileImportType fileImportType,
                                                          @RequestPart("file") MultipartFile file) throws IOException {
        transactionService.importFile(accountId, file, fileImportType);

        var apiResponse = ApiResponseHandler.generateSuccess("Import file of transactions successfully", SUCCESS_CREATE_TRANSACTION, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
