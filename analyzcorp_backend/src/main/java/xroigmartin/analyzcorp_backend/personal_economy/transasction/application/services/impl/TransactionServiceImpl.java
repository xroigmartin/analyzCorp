package xroigmartin.analyzcorp_backend.personal_economy.transasction.application.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.services.AccountService;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.application.services.TransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.application.utils.TransactionUtils;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.exceptions.CreateTransactionException;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transasction.interfaces.dto.transaction.CreateTransactionDTO;

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
