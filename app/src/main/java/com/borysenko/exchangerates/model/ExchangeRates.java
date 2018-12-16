package com.borysenko.exchangerates.model;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 16:15
 */
public class ExchangeRates {
    private String rateDate;
    private String baseCurrency;
    private Rate[] rates;

    public ExchangeRates(String rateDate, String baseCurrency, Rate[] rates) {
        this.rateDate = rateDate;
        this.baseCurrency = baseCurrency;
        this.rates = rates;
    }

    public String getRateDate() {
        return rateDate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public Rate[] getRates() {
        return rates;
    }
}
