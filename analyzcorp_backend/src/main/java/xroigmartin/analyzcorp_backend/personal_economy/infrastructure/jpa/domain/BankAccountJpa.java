package xroigmartin.analyzcorp_backend.personal_economy.infrastructure.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "bank_account", schema = "personal_economy")
public class BankAccountJpa {

    @Id
    @ColumnDefault("nextval('personal_economy.bank_account_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "bank_name", nullable = false, length = Integer.MAX_VALUE)
    private String bankName;

    @Column(name = "iban", nullable = false, length = Integer.MAX_VALUE)
    private String iban;

    @Column(name = "alias", length = Integer.MAX_VALUE)
    private String alias;

}