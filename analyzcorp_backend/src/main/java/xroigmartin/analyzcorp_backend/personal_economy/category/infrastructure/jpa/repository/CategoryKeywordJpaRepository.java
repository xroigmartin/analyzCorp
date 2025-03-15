package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryKeywordJpa;

import java.util.Optional;

public interface CategoryKeywordJpaRepository extends JpaRepository<CategoryKeywordJpa, Long> {

    Optional<CategoryKeywordJpa> findByKeywordIgnoreCase(String keyword);

    // Buscar coincidencia parcial (subcadena)
    @Query("SELECT ck FROM CategoryKeywordJpa ck WHERE LOWER(:description) LIKE CONCAT('%', LOWER(ck.keyword), '%')")
    Optional<CategoryKeywordJpa> findByDescription(@Param("description") String description);
}
