package com.example.atm.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Junit class for DenominationTest . Contains test methods to verify total remaining denominations available
and total amount exceed th given input values or not
 */
class DenominationTest {

    Denomination denomination =null;

    @BeforeEach
    void setUp() {
        denomination = new Denomination();

    }


    @Test
    void verifyTotalRemainingAmount() {
        //check against initial values
        assertEquals(30100, denomination.getTotalRemainingAmount());
        //set new values
        denomination.setFiveHundreds(10);
        denomination.setTwoHundreds(5);
        denomination.setOneHundreds(10);
        denomination.setTens(10);
        assertEquals(7100, denomination.getTotalRemainingAmount());
    }

    @Test
    void isExceedTotalBalance() {
        //check against initial values
        assertTrue(denomination.isExceedTotalBalance(30101));
        assertFalse(denomination.isExceedTotalBalance(30100));
        //set new Values and verify
        denomination.setFiveHundreds(10);
        denomination.setTwoHundreds(5);
        denomination.setOneHundreds(10);
        denomination.setTens(10);
        assertTrue(denomination.isExceedTotalBalance(7101));
        assertFalse(denomination.isExceedTotalBalance(7100));
    }

    @AfterEach
    void tearDown() {
        denomination =null;

    }
}