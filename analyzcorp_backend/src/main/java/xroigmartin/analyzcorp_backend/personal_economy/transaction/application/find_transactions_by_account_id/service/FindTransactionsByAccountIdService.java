package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.command.FindTransactionsByAccountIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transactions_by_account_id.use_case.FindTransactionsByAccountIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FindTransactionsByAccountIdService implements FindTransactionsByAccountIdUseCase {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> handle(FindTransactionsByAccountIdCommand command) {
        return transactionRepository.findTransactionsByAccountId(command.accountId());
    }
}
