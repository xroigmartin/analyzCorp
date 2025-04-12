package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.command.FindTransactionByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.use_case.FindTransactionByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.TransactionNotFoundByIdException;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;

@Service
@AllArgsConstructor
public class FindTransactionByIdService implements FindTransactionByIdUseCase {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction handle(FindTransactionByIdCommand command) {
        return transactionRepository.findTransactionById(command.id())
                .orElseThrow(() -> new TransactionNotFoundByIdException(command.id()));
    }

}
