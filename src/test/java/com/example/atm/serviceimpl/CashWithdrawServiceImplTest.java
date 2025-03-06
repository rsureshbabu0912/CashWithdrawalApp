package com.example.atm.serviceimpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Junit test class for CashWithdrawServiceImpl - checks for Cashwithdraw and denominations remaining
 */
class CashWithdrawServiceImplTest {
    CashWithdrawServiceImpl cashWithdrawServiceImpl = null;

    @BeforeEach
    void setUp() {
        cashWithdrawServiceImpl = new CashWithdrawServiceImpl();

    }

    /*
    Test Withdraw positive scenario
     */
    @Test
    void cashWithdraw_Pass() {
        boolean result = cashWithdrawServiceImpl.doCashWithdraw(24200);
        assertTrue(result);
    }

    /*
    Test Withdraw negative scenario
     */
    @Test
    void cashWithdraw_Fail() {
        boolean result = cashWithdrawServiceImpl.doCashWithdraw(24201);
        assertFalse(result);
    }

    /*
   Test Withdraw functionality and checks the denomination values before and after withdraw function scenario
    */
    @Test
    void withdrawCashAndUpdateDenomination_500_And_200() {
        assertEquals(50, cashWithdrawServiceImpl.getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawServiceImpl.getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getTens());
        assertTrue(cashWithdrawServiceImpl.withdrawCashAndUpdateDenomination(24200));
        //check for remaining denomination count and total remianing amount
        assertEquals(2, cashWithdrawServiceImpl.getDenomination().getFiveHundreds());
        assertEquals(19, cashWithdrawServiceImpl.getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getTens());

    }

    /*
  Test Withdraw functionality and checks the denomination values before and after withdraw function scenario
   */
    @Test
    void withdrawCashAndUpdateDenomination_200_And_10() {
        assertEquals(50, cashWithdrawServiceImpl.getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawServiceImpl.getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getTens());
        assertTrue(cashWithdrawServiceImpl.withdrawCashAndUpdateDenomination(450));
        assertEquals(50, cashWithdrawServiceImpl.getDenomination().getFiveHundreds());
        assertEquals(18, cashWithdrawServiceImpl.getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getOneHundreds());
        assertEquals(5, cashWithdrawServiceImpl.getDenomination().getTens());

    }

    /*
  Test Withdraw functionality and checks the denomination values before and after withdraw function scenario
   */
    @Test
    void withdrawCashAndUpdateDenomination_100_And_10() {
        assertEquals(50, cashWithdrawServiceImpl.getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawServiceImpl.getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawServiceImpl.getDenomination().getTens());
        assertTrue(cashWithdrawServiceImpl.withdrawCashAndUpdateDenomination(150));
        assertEquals(50, cashWithdrawServiceImpl.getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawServiceImpl.getDenomination().getTwoHundreds());
        assertEquals(9, cashWithdrawServiceImpl.getDenomination().getOneHundreds());
        assertEquals(5, cashWithdrawServiceImpl.getDenomination().getTens());

    }


    @AfterEach
    void tearDown() {
        cashWithdrawServiceImpl = null;

    }
}


