package bank.domain;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountRepository {

    private Map<AccountId, Account> accountMap;

    public AccountRepository() {
        this.accountMap = Maps.newHashMap();
    }

    public void saveAccount(AccountId accountId, Account account) {
        accountMap.put(accountId, account);
    }

    public Account loadAccount(AccountId accountId) {
        Account account = accountMap.get(accountId);
        if (account == null) {
            throw new RuntimeException(String.format("Account not found: %s", accountId.getId()));
        }
        return accountMap.get(accountId);
    }
}
