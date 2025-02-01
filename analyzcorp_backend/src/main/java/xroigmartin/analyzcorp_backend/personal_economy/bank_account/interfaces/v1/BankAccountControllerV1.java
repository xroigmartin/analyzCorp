package xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.v1;

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
import org.springframework.web.bind.annotation.RestController;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.dto.account.UpdateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.AccountService;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.BankAccountControllerConstants.BANK_ACCOUNT_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.BankAccountControllerConstants.SUCCESS_CREATE_BANK_ACCOUNT;
import static xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.BankAccountControllerConstants.SUCCESS_FIND_ALL_BANK_ACCOUNT;
import static xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.utils.BankAccountControllerConstants.SUCCESS_GET_ACCOUNT_BY_ID;

@RestController
@RequestMapping(BANK_ACCOUNT_PATH)
@AllArgsConstructor
public final class BankAccountControllerV1 {

    private final AccountService bankAccountService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AccountDTO>>> findAllBankAccounts(){
        var bankAccounts = bankAccountService.findAllAccount();
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccounts, SUCCESS_FIND_ALL_BANK_ACCOUNT, HttpStatus.OK.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> getAccountById(@PathVariable Long accountId){
        var bankAccount = bankAccountService.findAccountById(accountId);
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccount, SUCCESS_GET_ACCOUNT_BY_ID, HttpStatus.OK.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> createBankAccount(@RequestBody @Valid CreateAccountDTO createAccountDTO){
        var bankAccount = bankAccountService.createAccount(createAccountDTO);
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccount, SUCCESS_CREATE_BANK_ACCOUNT, HttpStatus.CREATED.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id-bank-account}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> updateBankAccount(@PathVariable("id-bank-account") Long id,
                                                                     @RequestBody @Valid UpdateAccountDTO updateAccountDTO){
        var bankAccount = bankAccountService.updateAccount(updateAccountDTO, id);
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccount, SUCCESS_CREATE_BANK_ACCOUNT, HttpStatus.OK.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
