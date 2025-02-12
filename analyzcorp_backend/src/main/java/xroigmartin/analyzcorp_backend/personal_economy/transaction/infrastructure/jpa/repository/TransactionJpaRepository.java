package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.domain.TransactionJpa;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpa, Long> {
}
