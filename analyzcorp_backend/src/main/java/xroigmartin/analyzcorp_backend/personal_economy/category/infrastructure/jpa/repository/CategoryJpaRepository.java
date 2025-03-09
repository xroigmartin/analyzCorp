package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpa, Long> {
}
