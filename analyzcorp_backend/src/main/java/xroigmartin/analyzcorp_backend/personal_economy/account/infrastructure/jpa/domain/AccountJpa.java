package xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account", schema = "personal_economy")
public class AccountJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_name", nullable = false, length = 255)
    private String bankName;

    @Column(name = "iban", nullable = false, length = 34, unique = true)
    private String iban;

    @Column(name = "alias", length = 255)
    private String alias;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by", nullable = false, length = 50)
    private String updatedBy;

    public static AccountJpa create(Long id, String bankName, String iban, String alias, String createdBy, String updatedBy) {
        return AccountJpa.create(id, bankName, iban, alias, createdBy, updatedBy, OffsetDateTime.now(), OffsetDateTime.now());
    }

    public static AccountJpa create(Long id, String bankName, String iban, String alias, String createdBy, String updatedBy, OffsetDateTime createdAt, OffsetDateTime updatedAt){
        return AccountJpa.builder()
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