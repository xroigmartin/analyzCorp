package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.repository.TransactionJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.utils.TransactionJpaUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TransactionJpaService implements TransactionRepository {

    private final TransactionJpaRepository transactionJpaRepository;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) {
        var saved = transactionJpaRepository.save(TransactionJpaUtils.toEntity(transaction));
        return TransactionJpaUtils.toDomain(saved);
    }

    @Override
    @Transactional
    public void createListOfTransaction(List<Transaction> transactions) {
        var transactionsJpa = transactions.stream().map(TransactionJpaUtils::toEntity).toList();
        this.transactionJpaRepository.saveAll(transactionsJpa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        return this.transactionJpaRepository.findTransactionsByAccountJpa_Id(accountId)
                .stream()
                .map(TransactionJpaUtils::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findTransactionById(Long transactionId) {
        return this.transactionJpaRepository.findById(transactionId)
                .map(TransactionJpaUtils::toDomain);
    }
}
