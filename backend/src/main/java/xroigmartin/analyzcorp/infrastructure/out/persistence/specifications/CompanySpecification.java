package xroigmartin.analyzcorp.infrastructure.out.persistence.specifications;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import xroigmartin.analyzcorp.infrastructure.out.persistence.entity.CompanyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class CompanySpecification {

    public static Specification<CompanyEntity> findByNameTickerCik(String name, String ticker, String cik) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(containsIgnoreCase(cb, root, CompanyEntity.Fields.name, name))
                    .ifPresent(predicates::add);

            Optional.ofNullable(containsIgnoreCase(cb, root, CompanyEntity.Fields.ticker, ticker))
                    .ifPresent(predicates::add);

            Optional.ofNullable(containsIgnoreCase(cb, root, CompanyEntity.Fields.cik, cik))
                    .ifPresent(predicates::add);

            return predicates.isEmpty()
                    ? cb.conjunction()
                    : cb.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Predicate containsIgnoreCase(CriteriaBuilder cb, Root<?> root, String field, String value) {
        return StringUtils.isNotBlank(value)
                ? cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%")
                : null;
    }

}
