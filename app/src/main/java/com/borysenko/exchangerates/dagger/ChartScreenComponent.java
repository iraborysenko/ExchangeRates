package com.borysenko.exchangerates.dagger;

import com.borysenko.exchangerates.ui.chart.ChartActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 00:59
 */
@Singleton
@Component(modules = ChartScreenModule.class)
public interface ChartScreenComponent {
    void inject(ChartActivity chartActivity);
}

