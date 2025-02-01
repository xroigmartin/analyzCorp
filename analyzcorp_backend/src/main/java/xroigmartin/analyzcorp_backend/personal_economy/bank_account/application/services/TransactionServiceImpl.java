package xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.interfaces.dto.transaction.CreateTransactionDTO;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.interfaces.TransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.application.utils.TransactionUtils;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.exceptions.CreateTransactionException;

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
}
