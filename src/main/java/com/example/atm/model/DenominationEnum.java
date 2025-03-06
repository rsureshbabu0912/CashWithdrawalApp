package com.example.atm.model;

public enum DenominationEnum {

    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    ONE_HUNDRED(100),
    TEN(10);

    public final Integer denomination;

    private DenominationEnum(Integer denomination) {
        this.denomination = denomination;

    }

    public int getValue() { return denomination; }
}
