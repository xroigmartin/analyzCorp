package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model;

import lombok.Builder;
import lombok.Getter;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Builder
@Getter
public class Budget {
    private Long id;
    private Category category;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;

    public void updateAmount(BigDecimal newAmount) {
        if(newAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.amount = newAmount;
        updateUpdatedAt();
    }

    public void updateUpdatedBy(String newUpdatedBy) {
        this.updatedBy = newUpdatedBy;
        updateUpdatedAt();
    }

    private void updateUpdatedAt(){
        this.updatedAt = OffsetDateTime.now();
    }
}
