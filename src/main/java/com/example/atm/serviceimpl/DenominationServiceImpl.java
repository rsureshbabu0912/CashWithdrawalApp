package com.example.atm.serviceimpl;

import com.example.atm.model.Denomination;
import com.example.atm.model.DenominationEnum;
import com.example.atm.service.DenominationService;

public class DenominationServiceImpl implements DenominationService {

    Denomination denomination;

    public DenominationServiceImpl() {
        this.denomination = new Denomination();
    }

    @Override
    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public int  dispenseFiveHundreds(final int amountToWithDraw) {
        int noOfFiveHundreds = amountToWithDraw / DenominationEnum.FIVE_HUNDRED.getValue();
        int noOfFiveHundredDeducted = 0;

        //logic to calculate available 500s and update the remaining

        if (denomination.getFiveHundreds() > 0) {
            if (denomination.getFiveHundreds() >= noOfFiveHundreds) {
                noOfFiveHundredDeducted = noOfFiveHundreds;
                denomination.setFiveHundreds(denomination.getFiveHundreds() - noOfFiveHundreds);
            } else {
                noOfFiveHundredDeducted = noOfFiveHundreds - Math.abs(denomination.getFiveHundreds() - noOfFiveHundreds);
                denomination.setFiveHundreds(denomination.getFiveHundreds() - noOfFiveHundredDeducted);
            }

        }
        return noOfFiveHundredDeducted;
    }

    @Override
    public int dispenseTwoHundreds(final int amountToWithDraw) {
        int noOfTwoHundreds = amountToWithDraw / DenominationEnum.TWO_HUNDRED.getValue();
        int noOfTwoHundredDeducted = 0;

        //logic to calculate available 200s and update the remaining

        if (denomination.getTwoHundreds() > 0) {
            if (denomination.getTwoHundreds() >= noOfTwoHundreds) {
                noOfTwoHundredDeducted = noOfTwoHundreds;
                denomination.setTwoHundreds(denomination.getTwoHundreds() - noOfTwoHundreds);
            } else {
                noOfTwoHundredDeducted = noOfTwoHundreds - Math.abs(denomination.getTwoHundreds() - noOfTwoHundreds);
                denomination.setTwoHundreds(denomination.getTwoHundreds() - noOfTwoHundredDeducted);
            }

        }
        return noOfTwoHundredDeducted;
    }

    @Override
    public int  dispenseOneHundreds(final int amountToWithDraw) {
        int noOfOneHundreds = amountToWithDraw / DenominationEnum.ONE_HUNDRED.getValue();
        int noOfOneHundredDeducted = 0;

        //logic to calculate available 100s and update the remaining

        if (denomination.getOneHundreds() > 0) {
            if (denomination.getOneHundreds() >= noOfOneHundreds) {
                noOfOneHundredDeducted = noOfOneHundreds;
                denomination.setOneHundreds(denomination.getOneHundreds() - noOfOneHundreds);
            } else {
                noOfOneHundredDeducted = noOfOneHundreds - Math.abs(denomination.getOneHundreds() - noOfOneHundreds);
                denomination.setOneHundreds(denomination.getOneHundreds() - noOfOneHundredDeducted);
            }

        }
        return noOfOneHundredDeducted;
    }

    @Override
    public int  dispenseTens(final int amountToWithDraw) {
        int noOfTens = amountToWithDraw / DenominationEnum.TEN.getValue();
        int noOfTenDeducted = 0;

        //logic to calculate available 10s and update the remaining

        if (denomination.getOneHundreds() > 0) {
            if (denomination.getTens() >= noOfTens) {
                noOfTenDeducted = noOfTens;
                denomination.setTens(denomination.getTens() - noOfTens);
            } else {
                noOfTenDeducted = noOfTens - Math.abs(denomination.getTens() - noOfTens);
                denomination.setTens(denomination.getTens() - noOfTenDeducted);
            }

        }
        return noOfTenDeducted;
    }

    @Override
    public int getTotalRemainingAmount() {

        return  DenominationEnum.FIVE_HUNDRED.getValue() * denomination.getFiveHundreds() + DenominationEnum.TWO_HUNDRED.getValue() * denomination.getTwoHundreds() +
                DenominationEnum.ONE_HUNDRED.getValue() * denomination.getOneHundreds() + DenominationEnum.TEN.getValue() * denomination.getTens();
    }

    @Override
    public boolean isExceedTotalBalance(int amount) {

        return  amount > getTotalRemainingAmount();
    }

}
