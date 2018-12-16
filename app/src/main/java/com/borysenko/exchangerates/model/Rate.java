package com.borysenko.exchangerates.model;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 15:51
 */
public class Rate {
    private String currency;
    private float saleRateNB;
    private float purchaseRateNB;
    private float saleRate;
    private float purchaseRate;

    public Rate(String currency, float saleRateNB, float purchaseRateNB,
                float saleRate, float purchaseRate) {
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public String getCurrency() {
        return currency;
    }

    public float getSaleRateNB() {
        return saleRateNB;
    }

    public float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public float getSaleRate() {
        return saleRate;
    }

    public float getPurchaseRate() {
        return purchaseRate;
    }
}
