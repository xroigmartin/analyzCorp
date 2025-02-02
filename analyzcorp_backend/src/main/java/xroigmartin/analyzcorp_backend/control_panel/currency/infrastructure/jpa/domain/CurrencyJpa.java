package xroigmartin.analyzcorp_backend.control_panel.currency.infrastructure.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "currencies", schema = "control_panel")
public class CurrencyJpa {

    @Id
    @Column(length = 3, nullable = false, updatable = false)
    private String code;

    @Column(length = 50, nullable = false)
    private String name;
}
