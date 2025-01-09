package xroigmartin.analyzcorp_backend.personal_economy.bank_account.infrastructure.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_category", schema = "personal_economy")
public class TransactionCategoryJpa {

    @Id
    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionJpa transactionJpa;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryJpa categoryJpa;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private Instant createdAt;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private Instant updatedAt;

    @Column(name = "updated_by", nullable = false, length = 50)
    private String updatedBy;

}
