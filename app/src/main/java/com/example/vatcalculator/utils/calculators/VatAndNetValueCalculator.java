package com.example.vatcalculator.utils.calculators;

import com.example.vatcalculator.common.enums.VatValue;

import java.util.Objects;
import java.util.Optional;

public class VatAndNetValueCalculator {

    public static Optional<CalculatedPrice> calculate(String grossValue, VatValue vatValue) {
        if (Objects.isNull(grossValue)) {
            return Optional.empty();
        }
        String grossTrimmed = grossValue.trim();
        if (grossTrimmed.isEmpty()) {
            return Optional.empty();
        }

        int gross = (int) (Float.parseFloat(grossTrimmed) * 100f);

        int net = (int) Math.ceil(gross / vatValue.getValue());
        int vat = gross - net;
        return Optional.of(new CalculatedPrice(gross, net, vat));
    }
}