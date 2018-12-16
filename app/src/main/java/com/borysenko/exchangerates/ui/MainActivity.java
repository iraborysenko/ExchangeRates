package com.borysenko.exchangerates.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.borysenko.exchangerates.R;
import com.borysenko.exchangerates.dagger.DaggerMainScreenComponent;
import com.borysenko.exchangerates.dagger.MainScreenModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreen.View {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainScreenComponent.builder()
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);
        mainPresenter.loadRates();
    }
}

