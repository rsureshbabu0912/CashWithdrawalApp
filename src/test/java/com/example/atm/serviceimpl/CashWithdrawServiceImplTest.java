package com.example.atm.serviceimpl;

import com.example.atm.model.Denomination;
import com.example.atm.service.DenominationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Junit test class for CashWithdrawServiceImpl - checks for Cashwithdraw and denominations remaining
 */
class CashWithdrawServiceImplTest {
    CashWithdrawServiceImpl cashWithdrawService = null;


    @BeforeEach
    void setUp() {
        cashWithdrawService = new CashWithdrawServiceImpl(new DenominationServiceImpl(new Denomination()));

    }

    /*
    Test Withdraw positive scenario
     */
    @Test
    void cashWithdraw_Pass() {
        boolean result = cashWithdrawService.doCashWithdraw(24200);
        assertTrue(result);
    }

    /*
    Test Withdraw negative scenario
     */
    @Test
    void cashWithdraw_Fail() {
        boolean result = cashWithdrawService.doCashWithdraw(24201);
        assertFalse(result);
    }

    /*
   Test Withdraw functionality and checks the denomination values before and after withdraw function scenario
    */
    @Test
    void withdrawCashAndUpdateDenomination_500_And_200() {
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertTrue(cashWithdrawService.withdrawCashAndUpdateDenomination(24200));
        //check for remaining denomination count and total remaining amount
        assertEquals(2, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(19, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertEquals(5900, cashWithdrawService.getDenominationService().getTotalRemainingAmount());

        //second iteration and validate the remaining denomonation count
        assertTrue(cashWithdrawService.withdrawCashAndUpdateDenomination(5000));
        assertEquals(0, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(0, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(8, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertEquals(900, cashWithdrawService.getDenominationService().getTotalRemainingAmount());

    }

    /*
  Test Withdraw functionality and checks the denomination values before and after withdraw function scenario
   */
    @Test
    void withdrawCashAndUpdateDenomination_200_And_10() {
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertTrue(cashWithdrawService.withdrawCashAndUpdateDenomination(450));
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(18, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(5, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertEquals(29650, cashWithdrawService.getDenominationService().getTotalRemainingAmount());
        //second iteration and validate the remaining denomonation count
        assertTrue(cashWithdrawService.withdrawCashAndUpdateDenomination(410));
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(16, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(4, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertEquals(29240, cashWithdrawService.getDenominationService().getTotalRemainingAmount());
    }

    /*
  Test Withdraw functionality and checks the denomination values before and after withdraw function scenario
   */
    @Test
    void withdrawCashAndUpdateDenomination_100_And_10() {
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(10, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertTrue(cashWithdrawService.withdrawCashAndUpdateDenomination(150));
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(9, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(5, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertEquals(29950, cashWithdrawService.getDenominationService().getTotalRemainingAmount());
        //second iteration and validate the remaining denomonation count
        assertTrue(cashWithdrawService.withdrawCashAndUpdateDenomination(110));
        assertEquals(50, cashWithdrawService.getDenominationService().getDenomination().getFiveHundreds());
        assertEquals(20, cashWithdrawService.getDenominationService().getDenomination().getTwoHundreds());
        assertEquals(8, cashWithdrawService.getDenominationService().getDenomination().getOneHundreds());
        assertEquals(4, cashWithdrawService.getDenominationService().getDenomination().getTens());
        assertEquals(29840, cashWithdrawService.getDenominationService().getTotalRemainingAmount());
    }
    /**
     * Checks for concurrent  users test
     */

    @Test
    void verifyCashWithdrawWithMultipleUsers() throws Exception{
        Thread user1 = new Thread(() -> cashWithdrawService.doCashWithdraw(1000));
        Thread user2 = new Thread(() -> cashWithdrawService.doCashWithdraw(12000));
        Thread user3 = new Thread(() -> cashWithdrawService.doCashWithdraw(200));
        user1.start();
        user2.start();
        user3.start();
        //Giving enough time to Threads to finish
        Thread.sleep(6000);
        assertEquals(16900, cashWithdrawService.getDenominationService().getTotalRemainingAmount());
    }


    @AfterEach
    void tearDown() {
        cashWithdrawService = null;

    }
}


