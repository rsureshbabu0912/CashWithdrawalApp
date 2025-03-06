package com.example.atm.serviceimpl;

import com.example.atm.model.Denomination;

import com.example.atm.service.CashWithdrawService;
import com.example.atm.utils.CashWithdrawUtility;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.atm.utils.CashWithdrawUtility.DENOMINATION_NOTES;

/**
 * Main Implementation class performing cash Withdrawal and updating the denomination total remaining summary
 * It contains methods to check for max limit to withdraw and whether valid denomination is passed in input amount.
 * Also checks against total remaining balance before each cash withdrawal
 */
public class CashWithdrawServiceImpl implements CashWithdrawService {
    private static final Logger LOGGER = Logger.getLogger(CashWithdrawServiceImpl.class.getName());

    Denomination denomination;

    public CashWithdrawServiceImpl() {
        denomination = new Denomination();
    }

    public Denomination getDenomination() {
        return this.denomination;
    }

    /**
     * Cash withdrwaal method checks for max limit and valid denominations
     * Also checks on whether required denominations available for cash withdrawal
     * Main logic is synchornized to support multithreading when executed using  multiple threads
     *
     * @param amount
     * @return boolean
     */
    @Override
    public boolean doCashWithdraw(final int amount) {
        boolean isWithDrawSuccess = false;

        if (CashWithdrawUtility.isMaxWithdrawalLimitExceed(amount)) {
            LOGGER.log(Level.WARNING, " Withdrawal limit reached, should be less than or equal to 25000");

            return isWithDrawSuccess;
        }

        if (CashWithdrawUtility.isNotValidDenominationLevel(amount)) {
            LOGGER.log(Level.WARNING, " Withdrawal Amount should be multiple of 10");

            return isWithDrawSuccess;
        }

        //The below code is synchronized so that withdraw function and denomination object can be thread safe
        // when main function executed by multiple threads
        synchronized (this) {
            if (denomination.isExceedTotalBalance(amount)) {
                LOGGER.log(Level.WARNING, " Withdrawal Amount exceeded available total amount available");

                return isWithDrawSuccess;
            }
            isWithDrawSuccess = withdrawCashAndUpdateDenomination(amount);

        }

        return isWithDrawSuccess;
    }

    /**
     * cash withdraw Logic and update Denominations count
     *
     * @param amount
     * @return Denominations
     */
    protected boolean withdrawCashAndUpdateDenomination(final int amount) {
        int amountToWithDraw = amount;
        Map<Integer, Integer> denominationCount = new HashMap<>();

        try {
            for (int i = 0; i < DENOMINATION_NOTES.length && amountToWithDraw != 0; i++) {

                if (amountToWithDraw >= DENOMINATION_NOTES[i]) {

                    if (DENOMINATION_NOTES[i] == CashWithdrawUtility.FIVE_HUNDRED) {
                        var noOfFiveHundreds = amountToWithDraw / DENOMINATION_NOTES[i];
                        denomination.setFiveHundreds(denomination.getFiveHundreds() - noOfFiveHundreds);
                        denominationCount.put(DENOMINATION_NOTES[i], noOfFiveHundreds);

                    } else if (DENOMINATION_NOTES[i] == CashWithdrawUtility.TWO_HUNDRED) {

                        var noOfTwoHundreds = amountToWithDraw / DENOMINATION_NOTES[i];
                        denomination.setTwoHundreds(denomination.getTwoHundreds() - noOfTwoHundreds);
                        denominationCount.put(DENOMINATION_NOTES[i], noOfTwoHundreds);

                    } else if (DENOMINATION_NOTES[i] == CashWithdrawUtility.ONE_HUNDRED) {

                        var noOfOneHundreds = amountToWithDraw / DENOMINATION_NOTES[i];
                        denomination.setOneHundreds(denomination.getOneHundreds() - noOfOneHundreds);
                        denominationCount.put(DENOMINATION_NOTES[i], noOfOneHundreds);

                    } else if (DENOMINATION_NOTES[i] == CashWithdrawUtility.TEN) {

                        var noOfTens = amountToWithDraw / DENOMINATION_NOTES[i];
                        denomination.setTens(denomination.getTens() - noOfTens);
                        denominationCount.put(DENOMINATION_NOTES[i], noOfTens);
                    }

                    amountToWithDraw = amountToWithDraw % DENOMINATION_NOTES[i];
                }
            }

            LOGGER.log(Level.INFO, "Balance Total amount remaining:" + denomination.getTotalRemainingAmount());

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Cash Withdraw failed with:" + ex.getMessage());
            return false;
        }
        return true;
    }
}

