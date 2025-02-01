package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.TransactionJpa;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpa, Long> {
}
