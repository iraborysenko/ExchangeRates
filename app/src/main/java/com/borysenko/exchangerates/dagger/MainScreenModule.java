package com.borysenko.exchangerates.dagger;

import com.borysenko.exchangerates.ui.main.MainScreen;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:49
 */
@Module(includes = NetModule.class)
public class MainScreenModule {
    private final MainScreen.View mView;

    public MainScreenModule(MainScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    MainScreen.View providesMainScreenView() {
        return mView;
    }
}
