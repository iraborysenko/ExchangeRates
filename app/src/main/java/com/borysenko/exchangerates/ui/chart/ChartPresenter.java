package com.borysenko.exchangerates.ui.chart;


import android.support.annotation.NonNull;
import android.util.Log;

import com.borysenko.exchangerates.model.ChartData;
import com.borysenko.exchangerates.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 00:59
 */
public class ChartPresenter implements ChartScreen.Presenter  {

    private ChartScreen.View mView;
    private List<ChartData> chartDataList = new ArrayList<>();

    @Inject
    ChartPresenter(ChartScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;

    @Override
    public void loadChartData(final List<String> dateRange) {
        //load data for chart
        //loading for each input day nbrate, privat sale and privat purchase
        //bad idea. extremely slow and unreliable

        for (String date: dateRange) {
            Call<ChartData> call =
                    apiInterface.getChartData(date);
            call.enqueue(new Callback<ChartData>() {
                @Override
                public void onResponse(@NonNull Call<ChartData>call,
                                       @NonNull Response<ChartData> response) {
                    ChartData chart = response.body();
                    assert chart != null;
                    addToChartList(chart, dateRange.size());
                }

                @Override
                public void onFailure(@NonNull Call<ChartData>call, @NonNull Throwable t) {
                    Log.e("error", t.toString());
                }
            });
        }
    }

    private void addToChartList(ChartData chart, int size) {
        //just saving data for each day to an one list
        chartDataList.add(chart);
        if (chartDataList.size()==size) {
            chartDataList.clear();
        }
    }
}
