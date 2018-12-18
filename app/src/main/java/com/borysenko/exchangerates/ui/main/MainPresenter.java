package com.borysenko.exchangerates.ui.main;


import android.support.annotation.NonNull;
import android.util.Log;

import com.borysenko.exchangerates.model.ExchangeRates;
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
public class MainPresenter implements MainScreen.Presenter {

    private MainScreen.View mView;

    @Inject
    MainPresenter(MainScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;

    @Override
    public void loadRates(String date) {
        //loading exchange rates for privat and nbu banks

        mView.setProgressBarVisible();
        Call<ExchangeRates> call =
                apiInterface.getRates(date);
        call.enqueue(new Callback<ExchangeRates>() {
            @Override
            public void onResponse(@NonNull Call<ExchangeRates>call,
                                   @NonNull Response<ExchangeRates> response) {
                ExchangeRates rates = response.body();
                assert rates != null;
                if (rates.getRates().length == 0) {
                    mView.clearTables();
                    mView.showErrorToast();
                }

                mView.fillPBTable(rates);
                mView.fillNBTable(rates);
                mView.setProgressBarInvisible();
            }

            @Override
            public void onFailure(@NonNull Call<ExchangeRates>call, @NonNull Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
