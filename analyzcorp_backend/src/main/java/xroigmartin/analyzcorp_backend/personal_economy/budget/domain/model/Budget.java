package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.budget.domain.exceptions.BudgetValidationException;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Budget {
    private Long id;
    private Category category;
    private Account account;
    private AmountValueObject amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;

    public void updateAmount(BigDecimal newAmount) {
        this.amount = new AmountValueObject(newAmount);
        updateUpdatedAt();
    }

    public void updateUpdatedBy(String newUpdatedBy) {
        this.updatedBy = newUpdatedBy;
        updateUpdatedAt();
    }

    public void updateStartDate(LocalDate newStartDate){
        validateDates(newStartDate, this.endDate);
        this.startDate = newStartDate;
        updateUpdatedAt();
    }

    public void updateEndDate(LocalDate newEndDate){
        validateDates(this.startDate, newEndDate);
        this.endDate = newEndDate;
        updateUpdatedAt();
    }

    private void updateUpdatedAt(){
        this.updatedAt = OffsetDateTime.now();
    }

    private void validateDates(LocalDate startDate, LocalDate endDate){
        if(startDate.isAfter(endDate)){
            throw new BudgetValidationException("Start date must be before end date of budget");
        }
    }

    public static Budget create(Category category, Account account, BigDecimal amount, LocalDate startDate, LocalDate endDate, String createdBy, String updatedBy) {

        if(startDate == null || endDate == null
                || startDate.isAfter(endDate)){
            throw new BudgetValidationException("Start date must be before end date of budget");
        }

        if(category == null){
            throw new BudgetValidationException("Category are required");
        }

        if(account == null){
            throw new BudgetValidationException("Account are required");
        }

        return Budget.builder()
                .category(category)
                .account(account)
                .amount(new AmountValueObject(amount))
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
