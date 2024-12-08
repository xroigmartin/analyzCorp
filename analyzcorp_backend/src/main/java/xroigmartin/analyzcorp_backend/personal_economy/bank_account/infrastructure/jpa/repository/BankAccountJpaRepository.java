package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain.BankAccountJpa;

public interface BankAccountJpaRepository extends JpaRepository<BankAccountJpa, Integer> {
}
