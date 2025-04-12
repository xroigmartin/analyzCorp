package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.command.FindCurrencyByCodeCommand;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.use_case.FindCurrencyByCodeUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.command.FindCategoryByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id.use_case.FindCategoryByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.command.FindTransactionByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.find_transaction_by_id.use_case.FindTransactionByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.command.UpdateTransactionCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.update_transaction.use_case.UpdateTransactionUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class UpdateTransactionService implements UpdateTransactionUseCase {

    private final FindTransactionByIdUseCase findTransactionByIdUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final FindCurrencyByCodeUseCase findCurrencyByCodeUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction handle(UpdateTransactionCommand command) {

        var transaction = findTransactionByIdUseCase.handle(FindTransactionByIdCommand.create(command.id()));
        var account = findAccountByIdUseCase.handle(FindAccountByIdCommand.create(command.accountId()));
        var currency = findCurrencyByCodeUseCase.handle(FindCurrencyByCodeCommand.create(command.currency()));
        var category = findCategoryByIdUseCase.handle(FindCategoryByIdCommand.create(command.categoryId()));

        var transactionUpdate = Transaction.create(transaction.getId(), new AmountValueObject(command.amount()), currency,
                command.date(), command.type(), command.description(), category, account, transaction.getCreatedAt(),
                transaction.getCreatedBy(), OffsetDateTime.now(), command.updatedBy());

        return this.transactionRepository.save(transactionUpdate);
    }
}
