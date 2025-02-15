package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.services.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.BankTransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.TransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.utils.TransactionUtils;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.CreateTransactionException;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public Transaction createTransaction(CreateTransactionDTO createTransaction) {

        if(createTransaction == null){
            throw new CreateTransactionException("Information of transaction is empty");
        }

        if(createTransaction.amount() == null){
            throw new CreateTransactionException("Amount of transaction is empty");
        }

        if(createTransaction.currency() == null){
            throw new CreateTransactionException("Currency is empty");
        }

        if(createTransaction.date() == null){
            throw new CreateTransactionException("Date is empty");
        }

        if(createTransaction.accountId() == null){
            throw new CreateTransactionException("Account Id is empty");
        }

        this.accountService.findAccountById(createTransaction.accountId());

        var newTransaction = TransactionUtils.convertCreateTransactionToTransaction(createTransaction);
        return this.transactionRepository.createTransaction(newTransaction);
    }

    @Override
    public void importFile(Long accountId, MultipartFile file, FileImportType fileImportType) throws IOException {

        List<Transaction> transactions = new ArrayList<>();

        Account account = accountService.findAccountById(accountId);

        BankTransactionService bankTransactionService = new CaixaBankTransactionServiceImpl();

        bankTransactionService.importFile(account, file, transactions, fileImportType);

        this.transactionRepository.createListOfTransaction(transactions);

    }

}
