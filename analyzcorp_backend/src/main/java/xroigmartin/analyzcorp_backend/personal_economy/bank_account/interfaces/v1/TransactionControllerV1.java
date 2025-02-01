package xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.v1;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.dto.transaction.TransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.TransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.TransactionControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import static xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.TransactionControllerUtils.SUCCESS_CREATE_TRANSACTION;
import static xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.TransactionControllerUtils.TRANSACTION_PATH;

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
}
