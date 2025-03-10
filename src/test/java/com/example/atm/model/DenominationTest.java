package com.example.atm.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Junit class for DenominationTest .
 */
class DenominationTest {

    Denomination denomination =null;

    @BeforeEach
    void setUp() {
        denomination = new Denomination();

    }


    @Test
    void verifyInitialAndUpdatedValues() {
        //check against initial values
        assertEquals(50, denomination.getFiveHundreds());
        assertEquals(20, denomination.getTwoHundreds());
        assertEquals(10, denomination.getOneHundreds());
        assertEquals(10, denomination.getTens());
        //set new values
        denomination.setFiveHundreds(10);
        denomination.setTwoHundreds(5);
        denomination.setOneHundreds(10);
        denomination.setTens(10);
        assertEquals(10, denomination.getFiveHundreds());
        assertEquals(5, denomination.getTwoHundreds());
        assertEquals(10, denomination.getOneHundreds());
        assertEquals(10, denomination.getTens());
    }

    @AfterEach
    void tearDown() {
        denomination =null;

    }
}