package com.borysenko.exchangerates.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.borysenko.exchangerates.model.ExchangeRates;
import com.borysenko.exchangerates.model.Rate;
import com.borysenko.exchangerates.retrofit.ApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:51
 */
public class MainPresenter implements MainScreen.Presenter{

    private MainScreen.View mView;

    @Inject
    MainPresenter(MainScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;

    @Override
    public void loadRates() {
        String date = "01.12.2014";
        Call<ExchangeRates> call =
                apiInterface.getRates(date);
        call.enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeRates>call,
                                   @NonNull Response<ExchangeRates> response) {
                ExchangeRates rates = response.body();
                assert rates != null;
                Rate[] r = rates.getRates();
                Log.e("HERE!", String.valueOf(r[0].getPurchaseRateNB()));
            }

            @Override
            public void onFailure(@NonNull Call<ExchangeRates>call, @NonNull Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
