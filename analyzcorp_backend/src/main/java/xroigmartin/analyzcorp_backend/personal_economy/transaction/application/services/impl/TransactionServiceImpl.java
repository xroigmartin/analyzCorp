package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.services.CurrencyService;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.services.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.CategoryService;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.BankTransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.TransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank.CaixaBankTransactionServiceImpl;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.utils.TransactionUtils;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.CreateTransactionException;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.interfaces.dto.transaction.UpdateTransactionDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final CurrencyService currencyService;

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

        var categories = this.categoryService.findCategories();
        Category category;

        if(createTransaction.categoryId() != null){
            category = categories.stream()
                    .filter(cat -> cat.id().equals(createTransaction.categoryId()))
                    .findFirst()
                    .orElseThrow(() -> new CreateTransactionException(String.format("Not exists category with id: %s", createTransaction.categoryId())));
        }
        else{
            category = categories.stream()
                    .filter(cat -> cat.name().equals("Gastos Varios"))
                    .findFirst()
                    .get();
        }


        this.accountService.findAccountById(createTransaction.accountId());

        var newTransaction = TransactionUtils.convertCreateTransactionToTransaction(createTransaction, category);

        return this.transactionRepository.createTransaction(newTransaction);
    }

    @Override
    public void importFile(Long accountId, MultipartFile file, FileImportType fileImportType) throws IOException {

        List<Transaction> transactions = new ArrayList<>();

        Account account = accountService.findAccountById(accountId).orElseThrow(() -> new RuntimeException());

        BankTransactionService bankTransactionService = new CaixaBankTransactionServiceImpl(this.categoryService);

        bankTransactionService.importFile(account, file, transactions, fileImportType);

        this.transactionRepository.createListOfTransaction(transactions);

    }

    @Override
    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        return this.transactionRepository.findTransactionsByAccountId(accountId);
    }

    @Override
    public Transaction updateTransaction(Long id, UpdateTransactionDTO updateTransaction) {
        if(id == null){
            throw new RuntimeException("Id of transaction is mandatory for updated");
        }

        if(updateTransaction == null){
            throw new RuntimeException("Information for update transaction is empty");
        }

        if(updateTransaction.accountId() == null){
            throw new RuntimeException("Account Id of transaction is mandatory for updated");
        }

        if(updateTransaction.currency() == null){
            throw new RuntimeException("Currency of transaction is mandatory for updated");
        }

        if(updateTransaction.categoryId() == null){
            throw new RuntimeException("Category Id of transaction is mandatory for updated");
        }

        if(updateTransaction.date() == null){
            throw new RuntimeException("Date of transaction is mandatory for updated");
        }

        if(updateTransaction.type() == null){
            throw new RuntimeException("Type of transaction is mandatory for updated");
        }

        var transaction = this.getTransactionById(id);
        this.accountService.findAccountById(updateTransaction.accountId());
        this.currencyService.findCurrencyByCode(updateTransaction.currency());
        var category = this.categoryService.getCategoryById(updateTransaction.categoryId());

        var transactionUpdate = TransactionUtils.convertUpdateTransactionToTransaction(updateTransaction, transaction, category);

        return this.transactionRepository.updateTransaction(transactionUpdate);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        if(id == null){
            throw new RuntimeException("Id of transaction is mandatory for search transaction");
        }

        return this.transactionRepository.getTransactionById(id);
    }

}
