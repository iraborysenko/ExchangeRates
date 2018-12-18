package com.borysenko.exchangerates.retrofit;

import com.borysenko.exchangerates.model.ChartData;
import com.borysenko.exchangerates.model.ExchangeRates;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 15:24
 */
public interface ApiInterface {

    String PB_URL = "https://api.privatbank.ua/p24api/";

    @GET("exchange_rates?json")
    Call<ExchangeRates> getRates(@Query("date") String date);

    @GET("exchange_rates?json")
    Call<ChartData> getChartData(@Query("date") String date);
}
