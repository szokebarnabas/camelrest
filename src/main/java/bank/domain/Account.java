package bank.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Account {

    private AccountId accountId;
    private Balance balance;

    public Account(AccountId accountId, Balance balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Balance debit(BigDecimal amount) {
        if (amount.compareTo(balance.getAmount()) == 1) {
            throw new RuntimeException("You don't have enough money...");
        }
        balance = balance.subtract(amount);
        return balance;
    }

    public Balance credit(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }

    public Balance balance() {
        return balance;
    }
}
