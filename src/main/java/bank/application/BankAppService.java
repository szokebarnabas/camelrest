package bank.application;

import bank.domain.Account;
import bank.domain.AccountId;
import bank.domain.Balance;

import java.math.BigDecimal;

public interface BankAppService {
    AccountId createAccount(Balance starterBalance);

    Account getAccount(AccountId accountId);

    Balance debit(AccountId accountId, BigDecimal amount);

    Balance credit(AccountId accountId, BigDecimal amount);

    Balance getBalance(AccountId accountId);
}
