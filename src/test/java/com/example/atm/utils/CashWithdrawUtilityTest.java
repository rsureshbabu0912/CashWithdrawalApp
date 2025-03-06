package com.example.atm.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CashWithdrawUtilityTest {


    @Test
    void isMaxWithdrawalLimit_ExceedTrue() {
        assertTrue(CashWithdrawUtility.isMaxWithdrawalLimitExceed(25001));
    }

    @Test
    void isMaxWithdrawalLimit_False() {
        assertFalse(CashWithdrawUtility.isMaxWithdrawalLimitExceed(25000));
    }

    @Test
    void isNotValidDenominationLevel_True() {
        assertTrue(CashWithdrawUtility.isNotValidDenominationLevel(24101));
    }

    @Test
    void isNotValidDenominationLevel_False() {
        assertFalse(CashWithdrawUtility.isNotValidDenominationLevel(24100));
    }
}