package xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.domain.CurrencyJpa;

public interface CurrencyJpaRepository extends JpaRepository<CurrencyJpa, String> {
}
