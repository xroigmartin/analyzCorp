package xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryKeywordJpa;

import java.util.Optional;

public interface CategoryKeywordJpaRepository extends JpaRepository<CategoryKeywordJpa, Long> {

    Optional<CategoryKeywordJpa> findByKeywordIgnoreCase(String keyword);

    @Query("SELECT ck FROM CategoryKeywordJpa ck WHERE LOWER(:keyword) LIKE CONCAT('%', LOWER(ck.keyword), '%')")
    Optional<CategoryKeywordJpa> findByKeyword(@Param("keyword") String keyword);
}
