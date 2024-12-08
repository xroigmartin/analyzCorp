package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain.BankAccountJpa;

public interface BankAccountJpaRepository extends JpaRepository<BankAccountJpa, Integer> {
}
