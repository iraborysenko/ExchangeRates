package com.borysenko.exchangerates.dagger;

import com.borysenko.exchangerates.ui.chart.ChartScreen;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 01:00
 */
@Module(includes = NetModule.class)
public class ChartScreenModule {
    private final ChartScreen.View mView;

    public ChartScreenModule(ChartScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    ChartScreen.View providesChartScreenView() {
        return mView;
    }
}
