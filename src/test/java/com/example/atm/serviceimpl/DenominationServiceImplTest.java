package com.example.atm.serviceimpl;

import com.example.atm.service.DenominationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DenominationServiceImplTest {

    DenominationService denominationService;

    @BeforeEach
    void setUp() {
        denominationService = new DenominationServiceImpl();

    }

    @Test
    void dispenseFiveHundreds() {
        //first withdrawal
        int noOfFiveHundredsDeducted = denominationService.dispenseFiveHundreds(14100);
        assertEquals(28, noOfFiveHundredsDeducted);
        //No of five hundreds remaining
        assertEquals(22, denominationService.getDenomination().getFiveHundreds());
        assertEquals(16100, denominationService.getTotalRemainingAmount());
        //second withdrawal
        noOfFiveHundredsDeducted = denominationService.dispenseFiveHundreds(10000);
        assertEquals(20, noOfFiveHundredsDeducted);
        //No of five hundreds remaining
        assertEquals(2, denominationService.getDenomination().getFiveHundreds());

    }

    @Test
    void dispenseFiveHundreds_without_500_denominations() {
        denominationService.dispenseFiveHundreds(240);
        assertEquals(50, denominationService.getDenomination().getFiveHundreds());
        assertEquals(30100, denominationService.getTotalRemainingAmount());
    }

    @Test
    void dispenseTwoHundreds() {
        //first withdrawal
        int noOfTwoHundredsDeducted = denominationService.dispenseTwoHundreds(3000);
        assertEquals(15, noOfTwoHundredsDeducted);
        //No of two  hundreds remaining
        assertEquals(5, denominationService.getDenomination().getTwoHundreds());
        assertEquals(27100, denominationService.getTotalRemainingAmount());
        //second withdrawal
        noOfTwoHundredsDeducted = denominationService.dispenseTwoHundreds(1000);
        assertEquals(5, noOfTwoHundredsDeducted);
        //No two  hundreds remaining
        assertEquals(0, denominationService.getDenomination().getTwoHundreds());
        assertEquals(26100, denominationService.getTotalRemainingAmount());
    }


    @Test
    void dispenseOneHundreds() {
        //first withdrawal
        int noOfOneHundredsDeducted = denominationService.dispenseOneHundreds(410);

        //No of One hundreds remaining
        assertEquals(6, denominationService.getDenomination().getOneHundreds());
        assertEquals(29700, denominationService.getTotalRemainingAmount());
        //second withdrawal
        noOfOneHundredsDeducted = denominationService.dispenseOneHundreds(200);
        assertEquals(2, noOfOneHundredsDeducted);
        assertEquals(29500, denominationService.getTotalRemainingAmount());

    }

    @Test
    void dispenseTens() {
        denominationService.dispenseTens(20);
        assertEquals(8, denominationService.getDenomination().getTens());
        assertEquals(30080, denominationService.getTotalRemainingAmount());
    }



    @Test
    void isExceedTotalBalance_True() {
        assertTrue(denominationService.isExceedTotalBalance(30101));
    }

    @Test
    void isExceedTotalBalance_False() {
        assertFalse(denominationService.isExceedTotalBalance(30100));
    }
}