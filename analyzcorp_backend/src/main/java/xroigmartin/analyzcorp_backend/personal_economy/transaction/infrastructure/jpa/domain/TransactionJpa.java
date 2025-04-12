package xroigmartin.analyzcorp_backend.personal_economy.transaction.infrastructure.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.domain.CurrencyJpa;
import xroigmartin.analyzcorp_backend.personal_economy.account.infrastructure.jpa.domain.AccountJpa;
import xroigmartin.analyzcorp_backend.personal_economy.category.infrastructure.jpa.domain.CategoryJpa;
import xroigmartin.analyzcorp_backend.personal_economy.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction", schema = "personal_economy")
public class TransactionJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "currency", nullable = false)
    private CurrencyJpa currency;

    @Column(name = "date", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime date;

    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountJpa accountJpa;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private CategoryJpa category;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at", updatable = false, nullable = false)
    private OffsetDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false, length = 50)
    private String updatedBy;

}
