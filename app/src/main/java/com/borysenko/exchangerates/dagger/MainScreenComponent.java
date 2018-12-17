package com.borysenko.exchangerates.dagger;

import com.borysenko.exchangerates.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:49
 */
@Singleton
@Component(modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity mainActivity);
}
