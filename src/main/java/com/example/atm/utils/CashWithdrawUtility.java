package com.example.atm.utils;

/**
 * Utility class to store constants and CashWithdraw utility methods
 */
public class CashWithdrawUtility {


    public static final int[] DENOMINATION_NOTES = {500, 200, 100, 10};

    public static final int MAX_AMOUNT_TO_WITHDRAW = 25000;

    public static final int FIVE_HUNDRED = 500;

    public static final int TWO_HUNDRED = 200;

    public static final int ONE_HUNDRED = 100;

    public static final int TEN = 10;

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
