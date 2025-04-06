package xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.v1;

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
import xroigmartin.analyzcorp_backend.personal_economy.account.application.services.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.AccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.CreateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.dto.UpdateAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils.AccountControllerUtils;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils.AccountControllerUtils.ACCOUNT_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils.AccountControllerUtils.SUCCESS_CREATE_BANK_ACCOUNT;
import static xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils.AccountControllerUtils.SUCCESS_FIND_ALL_BANK_ACCOUNT;
import static xroigmartin.analyzcorp_backend.personal_economy.account.interfaces.utils.AccountControllerUtils.SUCCESS_GET_ACCOUNT_BY_ID;

@RestController
@RequestMapping(ACCOUNT_PATH)
@AllArgsConstructor
public final class AccountControllerV1 {

    private final AccountService bankAccountService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<AccountDTO>>> findAllBankAccounts(){
        var accounts = bankAccountService.findAllAccount();

        var accountDtos = accounts.stream().map(AccountControllerUtils::convertAccountToAccountDTO).toList();

        var apiResponse = ApiResponseHandler.generateSuccess(accountDtos, SUCCESS_FIND_ALL_BANK_ACCOUNT, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> getAccountById(@PathVariable Long accountId){
        var account = bankAccountService.findAccountById(accountId).orElseThrow(() -> new RuntimeException());

        var accountDto = AccountControllerUtils.convertAccountToAccountDTO(account);

        var apiResponse = ApiResponseHandler.generateSuccess(accountDto, SUCCESS_GET_ACCOUNT_BY_ID, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> createBankAccount(@RequestBody @Valid CreateAccountDTO createAccountDTO){
        var newAccount = AccountControllerUtils.convertCreateAccountDtoToAccount(createAccountDTO);

        var account = bankAccountService.createAccount(newAccount);

        var accountDto = AccountControllerUtils.convertAccountToAccountDTO(account);

        var apiResponse = ApiResponseHandler.generateSuccess(accountDto, SUCCESS_CREATE_BANK_ACCOUNT, HttpStatus.CREATED.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id-bank-account}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<AccountDTO>> updateBankAccount(@PathVariable("id-bank-account") Long id,
                                                                     @RequestBody @Valid UpdateAccountDTO updateAccountDTO){
        var updateAccount = AccountControllerUtils.convertUpdateAccountDtoToAccount(updateAccountDTO, id);

        var account = bankAccountService.updateAccount(updateAccount);

        var accountDto = AccountControllerUtils.convertAccountToAccountDTO(account);

        var apiResponse = ApiResponseHandler.generateSuccess(accountDto, SUCCESS_CREATE_BANK_ACCOUNT, HttpStatus.OK.value());

        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
