package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.model.Transaction;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.domain.repository.TransactionRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.repository.TransactionJpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.utils.TransactionJpaUtils;

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
}
