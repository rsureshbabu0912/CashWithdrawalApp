package com.example.atm.service;

import com.example.atm.model.Denomination;

public interface DenominationService {

    int  dispenseFiveHundreds(final int amountToWithDraw);
    int dispenseTwoHundreds(final int amountToWithDraw);
    int dispenseOneHundreds(final int amountToWithDraw);
    int dispenseTens(final int amountToWithDraw);
    boolean isExceedTotalBalance(int amount);
    int getTotalRemainingAmount();
    Denomination getDenomination();


}
