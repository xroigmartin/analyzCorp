package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.domain.TransactionJpa;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.repository.TransactionJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.utils.TransactionJpaUtils;

@Service
@AllArgsConstructor
@Transactional
public class TransactionJpaService implements TransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction) {

        var transactionJpa = TransactionJpaUtils.convertToTransactionJpa(transaction);

        var transactionJpaCreated = this.transactionJpaRepository.save(transactionJpa);

        return TransactionJpaUtils.convertToTransaction(transactionJpaCreated);
    }

    @Override
    @Transactional
    public void createListOfTransaction(List<Transaction> transactions) {
        var transactionsJpa = transactions.stream().map(TransactionJpaUtils::convertToTransactionJpa).toList();
        this.transactionJpaRepository.saveAll(transactionsJpa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        List<TransactionJpa> transactions = this.transactionJpaRepository.findTransactionsByAccountJpa_Id(accountId);
        return transactions.stream().map(TransactionJpaUtils::convertToTransaction).toList();
    }
}
