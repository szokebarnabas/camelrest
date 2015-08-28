package bank.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountTest {

    private static final BigDecimal STARTER_BALANCE = BigDecimal.TEN;

    private Account testObj;

    @Before
    public void setUp() {
        testObj = new Account(Balance.of(STARTER_BALANCE));
    }

    @Test
    public void debitDecreasesTheBalance() {
        //act
        Balance balance = testObj.debit(BigDecimal.ONE);

        //assert
        assertEquals(new BigDecimal(9), balance.getBalance());
    }

//    @Test
//    public void debitThrowsExceptionWhenTheDebitAmountIsGreaterThenTheBalance() {
//        //act
//        Balance balance = testObj.debit(new BigDecimal(100));
//
//        //assert
//    }

    @Test
    public void creditIncreasesTheBalance() {
        //act
        Balance balance = testObj.credit(BigDecimal.ONE);

        //assert
        assertEquals(new BigDecimal(11), balance.getBalance());
    }

    @Test
    public void balanceReturnsTheActualBalance() {
        //act
        Balance balance = testObj.balance();

        //assert
        assertEquals(BigDecimal.TEN, balance.getBalance());
    }
}