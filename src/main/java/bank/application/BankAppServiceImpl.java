package bank.application;

import bank.domain.Account;
import bank.domain.AccountId;
import bank.domain.AccountRepository;
import bank.domain.Balance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Slf4j
public class BankAppServiceImpl implements BankAppService {

    @Autowired
    private AccountRepository repository;

    @Override
    public AccountId createAccount(Balance starterBalance) {
        AccountId accountId = AccountId.of(UUID.randomUUID().toString());
        Account account = new Account(accountId, starterBalance);
        repository.saveAccount(accountId, account);
        log.info("Account {} has been created: {}", accountId, account);
        return accountId;
    }

    @Override
    public Account getAccount(AccountId accountId) {
        return repository.loadAccount(accountId);
    }

    @Override
    public Balance debit(AccountId accountId, BigDecimal amount) {
        Account account = repository.loadAccount(accountId);
        account.debit(amount);
        repository.saveAccount(accountId, account);
        log.info("Account has been debited: {}, amount {}", account, amount);
        return account.getBalance();
    }

    @Override
    public Balance credit(AccountId accountId, BigDecimal amount) {
        Account account = repository.loadAccount(accountId);
        account.credit(amount);
        repository.saveAccount(accountId, account);
        log.info("Account has been credited: {}, amount {}", account, amount);
        return account.getBalance();
    }

    @Override
    public Balance getBalance(AccountId accountId) {
        return repository.loadAccount(accountId).balance();
    }
}
