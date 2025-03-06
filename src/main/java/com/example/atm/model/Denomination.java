package com.example.atm.model;

import java.util.logging.Logger;

/**
 * This Class is to store denominations summary and total remaining denominations
 * post each cash withdrawal
 * The denomination values can be externalized when using standard framework  driven like spring
 */
public class Denomination {
    private static final Logger LOGGER = Logger.getLogger(Denomination.class.getName());

    //setting initial default values which can externalized
    private int fiveHundreds = 50;

    private int twoHundreds = 20;

    private int oneHundreds = 10;

    private int tens = 10;

    private static final int FIVE_HUNDRED = 500;
    private static final int TWO_HUNDRED = 200;
    private static final int ONE_HUNDRED = 100;
    private static final int TEN = 10;


    public int getFiveHundreds() {
        return fiveHundreds;
    }

    public void setFiveHundreds(int fiveHundreds) {
        this.fiveHundreds = fiveHundreds;
    }

    public int getTwoHundreds() {
        return twoHundreds;
    }

    public void setTwoHundreds(int twoHundreds) {
        this.twoHundreds = twoHundreds;
    }

    public int getOneHundreds() {
        return oneHundreds;
    }

    public void setOneHundreds(int oneHundreds) {
        this.oneHundreds = oneHundreds;
    }

    public int getTens() {
        return tens;
    }

    public void setTens(int tens) {
        this.tens = tens;
    }

    public int getTotalRemainingAmount() {

        return  DenominationEnum.FIVE_HUNDRED.getValue() * getFiveHundreds() + DenominationEnum.TWO_HUNDRED.getValue() * this.getTwoHundreds() +
                DenominationEnum.ONE_HUNDRED.getValue() * getOneHundreds() + DenominationEnum.TEN.getValue() * this.getTens();
    }

    public boolean isExceedTotalBalance(int amount) {

         return  amount > getTotalRemainingAmount();
    }

    @Override
    public String toString() {
        return "Denominations{" +
                "fiveHundreds=" + fiveHundreds +
                ", twoHundreds=" + twoHundreds +
                ", oneHundreds=" + oneHundreds +
                ", tens=" + tens +
                '}';
    }
}
