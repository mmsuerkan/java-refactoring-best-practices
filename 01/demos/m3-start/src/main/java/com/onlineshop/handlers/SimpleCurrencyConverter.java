package com.onlineshop.handlers;

public class SimpleCurrencyConverter {

    private String currencyTo;

    public SimpleCurrencyConverter(String currencyTo) {
        this.currencyTo = currencyTo;
    }
    public double convert(double amount) {
        if(currencyTo.equals("EUR")) {
            return amount * 0.9;
        } else if (currencyTo.equals("CAD")) {
            return amount * 1.35;
        } else {
            throw new IllegalArgumentException("Unknown currency: " + currencyTo);
        }
    }
}
