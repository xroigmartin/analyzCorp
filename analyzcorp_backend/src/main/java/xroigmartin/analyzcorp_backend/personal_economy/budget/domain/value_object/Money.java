package xroigmartin.analyzcorp_backend.personal_economy.budget.domain.value_object;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        if(value == null || value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Value must be greater than zero");
        }
        this.value = value;
    }

    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
