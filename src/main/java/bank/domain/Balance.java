package bank.domain;


import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Balance {

    private final BigDecimal amount;

    public Balance subtract(BigDecimal amount) {
        return Balance.of(this.amount.subtract(amount));
    }

    public Balance add(BigDecimal amount) {
        return Balance.of(this.amount.add(amount));
    }
}
