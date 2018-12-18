package com.borysenko.exchangerates.model;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 10:56
 */
public class ChartData {

    private String date;
    private float nbRate;
    private float saleRate;
    private float purchaseRate;

    public ChartData(String date, float nbRate, float saleRate, float purchaseRate) {
        this.date = date;
        this.nbRate = nbRate;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public String getDate() {
        return date;
    }

    public float getNbRate() {
        return nbRate;
    }

    public float getSaleRate() {
        return saleRate;
    }

    public float getPurchaseRate() {
        return purchaseRate;
    }
}
