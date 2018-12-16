package com.borysenko.exchangerates.dagger;

import com.borysenko.exchangerates.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:41
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
}