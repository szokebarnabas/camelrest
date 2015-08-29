package bank.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AccountUTest {

    private static final BigDecimal STARTER_BALANCE = BigDecimal.TEN;

    private Account testObj;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        AccountId accountId = AccountId.of("1123-1231-4444-1111");
        testObj = new Account(accountId, Balance.of(STARTER_BALANCE));
    }

    @Test
    public void debitDecreasesTheBalance() {
        //act
        Balance balance = testObj.debit(BigDecimal.ONE);

        //assert
        assertEquals(new BigDecimal(9), balance.getAmount());
    }

    @Test
    public void debitThrowsExceptionWhenTheDebitAmountIsGreaterThenTheBalance() {
        //setup
        exception.expect(RuntimeException.class);

        //act
        testObj.debit(new BigDecimal(100));

        //assert
    }

    @Test
    public void creditIncreasesTheBalance() {
        //act
        Balance balance = testObj.credit(BigDecimal.ONE);

        //assert
        assertEquals(new BigDecimal(11), balance.getAmount());
    }

    @Test
    public void balanceReturnsTheActualBalance() {
        //act
        Balance balance = testObj.balance();

        //assert
        assertEquals(BigDecimal.TEN, balance.getAmount());
    }
}