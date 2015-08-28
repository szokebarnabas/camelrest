package bank.infrastructure;

import bank.domain.Balance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountListener {

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public void createAccount() {

    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public Balance getBalance() {
        return Balance.of(BigDecimal.TEN);
    }
}
