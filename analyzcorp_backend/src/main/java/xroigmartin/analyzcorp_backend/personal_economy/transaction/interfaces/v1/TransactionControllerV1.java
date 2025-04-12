package xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.v1;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.command.CreateTransactionCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.use_case.CreateTransactionUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.command.FindTransactionsByAccountIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.use_case.FindTransactionsByAccountIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.command.ImportFileCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.use_case.ImportFileUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.command.UpdateTransactionCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.use_case.UpdateTransactionUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.ImportTransactionsDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.TransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.UpdateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.io.IOException;
import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils.SUCCESS_CREATE_TRANSACTION;
import static xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils.SUCCESS_FIND_TRANSACTIONS_BY_ACCOUNT_ID;
import static xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils.SUCCESS_UPDATE_TRANSACTION;
import static xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.utils.TransactionControllerUtils.TRANSACTION_PATH;

@RestController
@RequestMapping(TRANSACTION_PATH)
@AllArgsConstructor
public class TransactionControllerV1 {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final ImportFileUseCase importFileUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final FindTransactionsByAccountIdUseCase findTransactionsByAccountIdUseCase;

    @PostMapping(value="", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<TransactionDTO>> createTransaction(@RequestBody @Valid CreateTransactionDTO createTransactionDTO){
        var command = CreateTransactionCommand.create(createTransactionDTO.amount(), createTransactionDTO.currency(),
                createTransactionDTO.categoryId(), createTransactionDTO.date(), createTransactionDTO.type(),
                createTransactionDTO.description(), createTransactionDTO.accountId(), createTransactionDTO.createdBy());

        var transaction = createTransactionUseCase.handle(command);

        var transactionDTO = TransactionControllerUtils.convertToTransactionDTO(transaction);

        var apiResponse = ApiResponseHandler.generateSuccess(transactionDTO, SUCCESS_CREATE_TRANSACTION, HttpStatus.CREATED.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping(value="/import_file")
    public ResponseEntity<ApiResponse<String>> importFile(@RequestBody() ImportTransactionsDTO importTransactionsDTO,
                                                          @RequestPart("file") MultipartFile file) throws IOException {
        var command = ImportFileCommand.create(importTransactionsDTO.accountId(), importTransactionsDTO.fileImportType(),
                file, importTransactionsDTO.createdBy());

        importFileUseCase.handle(command);

        var apiResponse = ApiResponseHandler.generateSuccess("Import file of transactions successfully", SUCCESS_CREATE_TRANSACTION, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ApiResponse<TransactionDTO>> updateTransaction(@PathVariable Long id, @RequestBody UpdateTransactionDTO updateTransaction){
        var command = UpdateTransactionCommand.create(id, updateTransaction.amount(), updateTransaction.currency(),
                updateTransaction.categoryId(), updateTransaction.date(), updateTransaction.type(), updateTransaction.description(),
                updateTransaction.accountId(), updateTransaction.updatedBy());

        var transaction = updateTransactionUseCase.handle(command);

        var transactionDTO = TransactionControllerUtils.convertToTransactionDTO(transaction);

        var apiResponse = ApiResponseHandler.generateSuccess(transactionDTO, SUCCESS_UPDATE_TRANSACTION, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<TransactionDTO>>> getAllTransactionsByAccountId(@RequestParam Long accountId) {
        var command = FindTransactionsByAccountIdCommand.create(accountId);

        var transactions = findTransactionsByAccountIdUseCase.handle(command);

        var transactionDtos = transactions.stream().map(TransactionControllerUtils::convertToTransactionDTO).toList();

        var apiResponse = ApiResponseHandler.generateSuccess(transactionDtos, SUCCESS_FIND_TRANSACTIONS_BY_ACCOUNT_ID, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
