package xroigmartin.analyzcorp_backend.personal_economy.account.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Account {
    private Long id;
    private String bankName;
    private String iban;
    private String alias;
    private String createdBy;
    private OffsetDateTime createdAt;
    private String updatedBy;
    private OffsetDateTime updatedAt;

    public void updateIban(String newIban){
        this.iban = newIban;
        updateUpdateAt();
    }

    public void updateBankName(String newBankName){
        this.bankName = newBankName;
        updateUpdateAt();
    }

    public void updateAlias(String newAlias){
        this.alias = newAlias;
        updateUpdateAt();
    }

    private void updateUpdateAt(){
        this.updatedAt = OffsetDateTime.now();
    }

    public static Account create(Long id, String bankName, String iban, String alias, String createdBy, String updatedBy){
        return Account.create(id, bankName, iban,
                alias, createdBy, updatedBy, OffsetDateTime.now(), OffsetDateTime.now());
    }

    public static Account create(Long id, String bankName, String iban, String alias, String createdBy, String updatedBy, OffsetDateTime createdAt, OffsetDateTime updatedAt){
        return Account.builder()
                .id(id)
                .bankName(bankName)
                .iban(iban)
                .alias(alias)
                .createdBy(createdBy)
                .createdAt(createdAt)
                .updatedBy(updatedBy)
                .updatedAt(updatedAt)
                .build();
    }
}
