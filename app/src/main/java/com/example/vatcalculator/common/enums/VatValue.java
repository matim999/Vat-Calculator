package com.example.vatcalculator.common.enums;

public enum VatValue {
    VAT_8_Percent(1.08f),
    VAT_23_Percent(1.23f);

    private final float value;

    VatValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
