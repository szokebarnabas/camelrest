package bank.domain;


import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Balance {

    private final BigDecimal balance;

    public Balance subtract(BigDecimal amount) {
        return Balance.of(balance.subtract(amount));
    }

    public Balance add(BigDecimal amount) {
        return Balance.of(balance.add(amount));
    }
}
