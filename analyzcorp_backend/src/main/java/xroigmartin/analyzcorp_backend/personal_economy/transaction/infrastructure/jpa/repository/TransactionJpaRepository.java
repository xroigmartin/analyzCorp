package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.domain.TransactionJpa;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpa, Long> {

    List<TransactionJpa> findTransactionsByAccountJpa_Id(Long accountId);
}
