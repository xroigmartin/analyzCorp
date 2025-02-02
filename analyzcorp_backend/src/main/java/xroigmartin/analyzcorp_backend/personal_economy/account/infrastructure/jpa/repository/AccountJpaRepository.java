package xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;

public interface AccountJpaRepository extends JpaRepository<AccountJpa, Long> {
}
