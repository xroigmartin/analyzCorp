package xroigmartin.analyzcorp_backend.personal_economy.interfaces;

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
import xroigmartin.analyzcorp_backend.personal_economy.application.dto.BankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.application.dto.CreateBankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.application.dto.UpdateBankAccountDTO;
import xroigmartin.analyzcorp_backend.personal_economy.application.interfaces.BankAccountService;
import xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.ApiResponse;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ApiResponseHandler;
import xroigmartin.analyzcorp_backend.shared.infrastructure.utils.ResponseEntityHandler;

import java.util.List;

import static xroigmartin.analyzcorp_backend.personal_economy.interfaces.utils.BankAccountControllerConstants.BANK_ACCOUNT_PATH;
import static xroigmartin.analyzcorp_backend.personal_economy.interfaces.utils.BankAccountControllerConstants.SUCCESS_CREATE_BANK_ACCOUNT;
import static xroigmartin.analyzcorp_backend.personal_economy.interfaces.utils.BankAccountControllerConstants.SUCCESS_FIND_ALL_BANK_ACCOUNT;

@RestController
@RequestMapping(BANK_ACCOUNT_PATH)
@AllArgsConstructor
public final class BankAccountControllerV1 {


    private final BankAccountService bankAccountService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<BankAccountDTO>>> findAllBankAccounts(){
        var bankAccounts = bankAccountService.findAllBankAccount();
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccounts, SUCCESS_FIND_ALL_BANK_ACCOUNT, HttpStatus.OK.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<BankAccountDTO>> createBankAccount(@RequestBody @Valid CreateBankAccountDTO createBankAccountDTO){
        var bankAccount = bankAccountService.createBankAccount(createBankAccountDTO);
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccount, SUCCESS_CREATE_BANK_ACCOUNT, HttpStatus.CREATED.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id-bank-account}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<BankAccountDTO>> updateBankAccount(@PathVariable("id-bank-account") Integer id,
                                                                        @RequestBody @Valid UpdateBankAccountDTO updateBankAccountDTO){
        var bankAccount = bankAccountService.updateBankAccount(updateBankAccountDTO, id);
        var apiResponse = ApiResponseHandler.generateSuccess(bankAccount, SUCCESS_CREATE_BANK_ACCOUNT, HttpStatus.OK.value());
        return ResponseEntityHandler.generate(apiResponse, HttpStatus.OK);
    }
}
