package com.borysenko.exchangerates.ui.chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.borysenko.exchangerates.R;
import com.borysenko.exchangerates.dagger.ChartScreenModule;
import com.borysenko.exchangerates.dagger.DaggerChartScreenComponent;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 00:58
 */
public class ChartActivity extends AppCompatActivity implements ChartScreen.View  {

    @Inject
    ChartPresenter chartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerChartScreenComponent.builder()
                .chartScreenModule(new ChartScreenModule(this))
                .build().inject(this);

    }


}
