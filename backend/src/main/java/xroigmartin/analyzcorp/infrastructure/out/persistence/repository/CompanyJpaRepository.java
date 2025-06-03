package xroigmartin.analyzcorp.infrastructure.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xroigmartin.analyzcorp.infrastructure.out.persistence.entity.CompanyEntity;

public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, String>, JpaSpecificationExecutor<CompanyEntity> {
}
