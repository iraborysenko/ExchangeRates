package com.borysenko.exchangerates;

import android.app.Application;

import com.borysenko.exchangerates.dagger.AppComponent;
import com.borysenko.exchangerates.dagger.DaggerAppComponent;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:35
 */
public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .build();
    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }
}