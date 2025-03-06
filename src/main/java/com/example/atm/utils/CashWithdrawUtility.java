package com.example.atm.utils;

/**
 * Utility class to store constants and CashWithdraw utility methods
 */
public class CashWithdrawUtility {


    public static final int[] DENOMINATION_NOTES = {500, 200, 100, 10};

    public static final int MAX_AMOUNT_TO_WITHDRAW = 25000;

    /**
     * Checks for max withdrawal Limit of 25000
     *
     * @param amount
     * @return boolean
     */
    public static boolean isMaxWithdrawalLimitExceed(final int amount) {

        return amount > MAX_AMOUNT_TO_WITHDRAW;
    }

    /**
     * Checks for valid denominations in the given input
     *
     * @param amount
     * @return boolean
     */
    public static boolean isNotValidDenominationLevel(final int amount) {

        return amount % 10 != 0;
    }


}
