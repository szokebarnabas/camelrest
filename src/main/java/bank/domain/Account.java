package bank.domain;

import java.math.BigDecimal;

public class Account {

    private Balance balance;

    public Account(Balance balance) {
        this.balance = balance;
    }

    public Balance debit(BigDecimal amount) {
        return balance.subtract(amount);
    }

    public Balance credit(BigDecimal amount) {
        return balance.add(amount);
    }

    public Balance balance() {
        return balance;
    }


}
