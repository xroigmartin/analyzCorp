package xroigmartin.analyzcorp_backend.personal_economy.transaction.application.services;

import org.springframework.web.multipart.MultipartFile;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface ImportMonthlyExtract {

    void importMonthlyExtract(Account account, MultipartFile file, List<Transaction> transactions, Category category) throws IOException;
}
