package com.borysenko.exchangerates.ui.chart;

import com.borysenko.exchangerates.retrofit.ApiInterface;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 00:59
 */
public class ChartPresenter implements ChartScreen.Presenter  {

    private ChartScreen.View mView;

    @Inject
    ChartPresenter(ChartScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;


}
