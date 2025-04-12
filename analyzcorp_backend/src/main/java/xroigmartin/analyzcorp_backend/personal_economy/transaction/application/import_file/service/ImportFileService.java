package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.command.FindAccountByIdCommand;
import xroigmartin.analyzcorp_backend.personal_economy.account.application.find_account_by_id.use_case.FindAccountByIdUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_all_categories.use_case.FindAllCategoriesUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.category.application.find_category_by_keyword.use_case.FindCategoryByKeywordUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.command.ImportBankTransactionsCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.command.ImportFileCommand;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.import_file.use_case.ImportFileUseCase;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.bank_transaction.service.caixa_bank.ImportCaixaBankTransactionsService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportFileService implements ImportFileUseCase {

    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private final FindCategoryByKeywordUseCase findCategoryByKeywordUseCase;
    private final TransactionRepository transactionRepository;

    @Override
    public void handle(ImportFileCommand command) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        var findAccountByIdCommand = FindAccountByIdCommand.create(command.accountId());
        var account = findAccountByIdUseCase.handle(findAccountByIdCommand);

        var importBankTransactionsCommand = ImportBankTransactionsCommand.create(account, command.file(), transactions, command.fileImportType(), command.createdBy());
        ImportCaixaBankTransactionsService bankTransactionService = new ImportCaixaBankTransactionsService(findAllCategoriesUseCase, findCategoryByKeywordUseCase);
        bankTransactionService.handle(importBankTransactionsCommand);

        this.transactionRepository.createListOfTransaction(transactions);
    }
}
