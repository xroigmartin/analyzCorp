package xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.domain.BudgetJpa;

public interface BudgetJpaRepository extends JpaRepository<BudgetJpa, Long>, JpaSpecificationExecutor<BudgetJpa> {
}
