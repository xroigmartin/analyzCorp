package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.BankTransactionService;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank.CaixaBankCuaderno43ExtractTransactionServiceImpl;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services.impl.caixa_bank.CaixaBankMonthlyExtractTransactionServiceImpl;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.FileImportType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

public final class CaixaBankTransactionServiceImpl implements BankTransactionService {

    @Override
    public void importFile(Account account, MultipartFile file, List<Transaction> transactions, FileImportType fileImportType) throws IOException {

        switch (fileImportType) {
            case CUADERNO_43 -> new CaixaBankCuaderno43ExtractTransactionServiceImpl()
                    .importCuaderno43(account, file, transactions);
            case EXTRACTO_MENSUAL -> new CaixaBankMonthlyExtractTransactionServiceImpl()
                    .importMensualExtract(account, file, transactions);
        }
    }
}
