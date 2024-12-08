package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_account", schema = "personal_economy")
public class BankAccountJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_account_seq")
    @SequenceGenerator(name = "bank_account_seq", sequenceName = "personal_economy.bank_account_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "bank_name", nullable = false, length = Integer.MAX_VALUE)
    private String bankName;

    @Column(name = "iban", nullable = false, length = Integer.MAX_VALUE)
    private String iban;

    @Column(name = "alias", length = Integer.MAX_VALUE)
    private String alias;

}