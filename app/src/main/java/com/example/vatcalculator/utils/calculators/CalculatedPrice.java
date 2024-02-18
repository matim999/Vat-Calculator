package com.example.vatcalculator.utils.calculators;

public class CalculatedPrice {
    private final int gross;
    private final int net;
    private final int vat;
    private final float grossFloat;
    private final float netFloat;
    private final float vatFloat;
    private final String grossString;
    private final String netString;
    private final String vatString;

    public CalculatedPrice(int gross, int net, int vat) {
        this.gross = gross;
        this.net = net;
        this.vat = vat;
        this.grossFloat = gross / 100F;
        this.netFloat = net / 100F;
        this.vatFloat = vat / 100F;
        this.grossString = String.format("%.2f", grossFloat);
        this.netString = String.format("%.2f", netFloat);
        this.vatString = String.format("%.2f", vatFloat);
    }

    public int getGross() {
        return gross;
    }

    public int getNet() {
        return net;
    }

    public int getVat() {
        return vat;
    }

    public float getGrossFloat() {
        return grossFloat;
    }

    public float getNetFloat() {
        return netFloat;
    }

    public float getVatFloat() {
        return vatFloat;
    }

    public String getGrossString() {
        return grossString;
    }

    public String getNetString() {
        return netString;
    }

    public String getVatString() {
        return vatString;
    }
}
