package com.borysenko.exchangerates.dagger;

import android.content.Context;

import com.borysenko.exchangerates.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:44
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    public App app() {
        return mApp;
    }

    @Provides
    @Singleton
    public Context applicationContext() {
        return mApp.getApplicationContext();
    }

}
