package com.example.atm.serviceimpl;

import com.example.atm.model.DenominationEnum;
import com.example.atm.service.CashWithdrawService;
import com.example.atm.service.DenominationService;
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
 *
 * Note-Please refer the corresponding Junit written to test for concurrent users
 */
public class CashWithdrawServiceImpl implements CashWithdrawService {
    private static final Logger LOGGER = Logger.getLogger(CashWithdrawServiceImpl.class.getName());

    DenominationService denominationService;


    public CashWithdrawServiceImpl(DenominationService denominationService) {
        this.denominationService = denominationService;
    }

    DenominationService getDenominationService() {
        return this.denominationService;
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
            if (denominationService.isExceedTotalBalance(amount)) {
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
    boolean withdrawCashAndUpdateDenomination(final int amount) {
        int amountToWithDraw = amount;
        Map<DenominationEnum, Integer> notesSummary = new HashMap<>();
        notesSummary.put(DenominationEnum.FIVE_HUNDRED, 0);
        notesSummary.put(DenominationEnum.TWO_HUNDRED, 0);
        notesSummary.put(DenominationEnum.ONE_HUNDRED, 0);
        notesSummary.put(DenominationEnum.TEN, 0);

        try {
            for (int i = 0; i < DENOMINATION_NOTES.length && amountToWithDraw != 0; i++) {

                if (amountToWithDraw >= DENOMINATION_NOTES[i]) {

                    if (DENOMINATION_NOTES[i] == DenominationEnum.FIVE_HUNDRED.getValue() && amountToWithDraw >= DenominationEnum.FIVE_HUNDRED.getValue()) {
                        int noOfFiveHundredsDeducted  = denominationService.dispenseFiveHundreds(amountToWithDraw);
                        amountToWithDraw = amountToWithDraw - (noOfFiveHundredsDeducted * DenominationEnum.FIVE_HUNDRED.getValue());
                        notesSummary.put(DenominationEnum.FIVE_HUNDRED, noOfFiveHundredsDeducted);

                    } else if (DENOMINATION_NOTES[i] == DenominationEnum.TWO_HUNDRED.getValue() && amountToWithDraw >= DenominationEnum.TWO_HUNDRED.getValue()) {
                        int noOfTwoHundredsDeducted = denominationService.dispenseTwoHundreds(amountToWithDraw);
                        amountToWithDraw = amountToWithDraw - (noOfTwoHundredsDeducted * DenominationEnum.TWO_HUNDRED.getValue());
                        notesSummary.put(DenominationEnum.TWO_HUNDRED, noOfTwoHundredsDeducted);

                    } else if (DENOMINATION_NOTES[i] == DenominationEnum.ONE_HUNDRED.getValue() && amountToWithDraw >= DenominationEnum.ONE_HUNDRED.getValue()) {
                        int noOfOneHundredsDeducted = denominationService.dispenseOneHundreds(amountToWithDraw);
                        amountToWithDraw = amountToWithDraw - (noOfOneHundredsDeducted * DenominationEnum.ONE_HUNDRED.getValue());
                        notesSummary.put(DenominationEnum.ONE_HUNDRED, noOfOneHundredsDeducted);

                    } else if (DENOMINATION_NOTES[i] == DenominationEnum.TEN.getValue() && amountToWithDraw >= DenominationEnum.TEN.getValue()) {
                        int noOfTensDeducted = denominationService.dispenseTens(amountToWithDraw);
                        amountToWithDraw = amountToWithDraw - (noOfTensDeducted * DenominationEnum.TEN.getValue());
                        notesSummary.put(DenominationEnum.TEN, noOfTensDeducted);
                    }

                }
            }


            System.out.println("No of 500s deducted : " + notesSummary.get(DenominationEnum.FIVE_HUNDRED));
            System.out.println("No of 200s deducted : " + notesSummary.get(DenominationEnum.TWO_HUNDRED));
            System.out.println("No of 100s deducted : " + notesSummary.get(DenominationEnum.ONE_HUNDRED));
            System.out.println("No of 10s deducted : " + notesSummary.get(DenominationEnum.TEN));

            System.out.println("**********************************");

            System.out.println("No of 500s remaining: " + denominationService.getDenomination().getFiveHundreds());
            System.out.println("No of 200s remaining: " + denominationService.getDenomination().getTwoHundreds());
            System.out.println("No of 100s remaining: " + denominationService.getDenomination().getOneHundreds());
            System.out.println("No of 10s remaining: " + denominationService.getDenomination().getTens());



            LOGGER.log(Level.INFO, "Balance Total amount remaining:" + denominationService.getTotalRemainingAmount());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Cash Withdraw failed with:" + ex.getMessage());
            return false;
        }
        return true;
    }
}

