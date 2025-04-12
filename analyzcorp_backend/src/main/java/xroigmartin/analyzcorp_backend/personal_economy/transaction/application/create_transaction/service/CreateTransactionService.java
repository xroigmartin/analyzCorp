package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.command.FindCurrencyByCodeCommand;
import xroigmartin.analyzcorp_backend.control_panel.currency.application.find_currency_by_code.use_case.FindCurrencyByCodeUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.command.FindCategoryByIdOrDefaultCommand;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_id_or_default.use_case.FindCategoryByIdOrDefaultUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.command.CreateTransactionCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.create_transaction.use_case.CreateTransactionUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;

@Service
@AllArgsConstructor
public class CreateTransactionService implements CreateTransactionUseCase {

    private final FindCurrencyByCodeUseCase findCurrencyByCodeUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final FindCategoryByIdOrDefaultUseCase findCategoryByIdOrDefaultUseCase;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction handle(CreateTransactionCommand command) {

        var amount = new AmountValueObject(command.amount());
        var currency = findCurrencyByCodeUseCase.handle(FindCurrencyByCodeCommand.create(command.currency()));
        var account = findAccountByIdUseCase.handle(FindAccountByIdCommand.create(command.accountId()));
        var category = findCategoryByIdOrDefaultUseCase.handle(FindCategoryByIdOrDefaultCommand.create(command.categoryId()));

        var newTransaction = Transaction.create(null, amount, currency, command.date(), command.type(),
                command.description(), category, account, command.createdBy(), command.createdBy());

        return this.transactionRepository.save(newTransaction);
    }
}
