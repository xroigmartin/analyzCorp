package xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.utils;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import xroigmartin.analyzcorp_backend.personal_economy.budget.infrastructure.jpa.domain.BudgetJpa;

@UtilityClass
public class BudgetJpaSpecificationUtils {

    public static Specification<BudgetJpa> budgetsOfYear(int year){
        return (root, query, criteriaBuilder) -> {
            Expression<String> startYear = criteriaBuilder.function(
                    "TO_CHAR", String.class, root.get("startDate"), criteriaBuilder.literal("YYYY")
            );

            Expression<String> endYear = criteriaBuilder.function(
                    "TO_CHAR", String.class, root.get("endDate"), criteriaBuilder.literal("YYYY")
            );

            Predicate startDateYear = criteriaBuilder.equal(startYear, String.valueOf(year));
            Predicate endDateYear = criteriaBuilder.equal(endYear, String.valueOf(year));

            return criteriaBuilder.and(startDateYear, endDateYear);
        };
    }
}
