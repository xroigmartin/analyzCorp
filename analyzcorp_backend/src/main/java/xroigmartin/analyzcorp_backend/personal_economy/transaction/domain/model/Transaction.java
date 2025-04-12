package xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xroigmartin.analyzcorp_backend.control_panel.currency.domain.model.Currency;
import xroigmartin.analyzcorp_backend.personal_economy.account.domain.model.Account;
import xroigmartin.analyzcorp_backend.personal_economy.category.domain.model.Category;
import xroigmartin.analyzcorp_backend.personal_economy.shared.domain.value_object.AmountValueObject;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.exceptions.TransactionValidationException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Transaction {

    private Long id;
    private AmountValueObject amount;
    private Currency currency;
    private OffsetDateTime date;
    private TransactionType type;
    private String description;
    private Category category;
    private Account account;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;

    public static Transaction create(Long id, AmountValueObject amount, Currency currency, OffsetDateTime date,
                                     TransactionType type,String description,Category category,Account account,
                                     String createdBy,String updatedBy){
        return Transaction.create(id, amount, currency, date, type, description, category, account, OffsetDateTime.now(), createdBy, OffsetDateTime.now(), updatedBy);
    }

    public static Transaction create(Long id, AmountValueObject amount, Currency currency, OffsetDateTime date,
                                     TransactionType type,String description,Category category,Account account,
                                     OffsetDateTime createdAt,String createdBy,OffsetDateTime updatedAt,String updatedBy){

        if(date == null){
            throw new TransactionValidationException("Date of transaction is empty");
        }

        return Transaction.builder()
                .id(id)
                .amount(amount)
                .currency(currency)
                .date(date)
                .type(type)
                .description(description)
                .category(category)
                .account(account)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .updatedAt(updatedAt)
                .updatedBy(updatedBy)
                .build();
    }

    public void updateAmount(BigDecimal amount){
        this.amount = new AmountValueObject(amount);
        updateUpdateAt();
    }

    public void updateCurrency(Currency currency){
        this.currency = currency;
        updateUpdateAt();
    }

    public void updateCategory(Category category){
        this.category = category;
        updateUpdateAt();
    }

    public void updateDate(OffsetDateTime date){
        this.date = date;
        updateUpdateAt();
    }

    public void updateType(TransactionType type){
        this.type = type;
        updateUpdateAt();
    }

    public void updateDescription(String description){
        this.description = description;
        updateUpdateAt();
    }

    public void updateAccount(Account account){
        this.account = account;
        updateUpdateAt();
    }

    public void updateUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
        updateUpdateAt();
    }

    private void updateUpdateAt(){
        this.updatedAt = OffsetDateTime.now();
    }
}
