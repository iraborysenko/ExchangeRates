package com.borysenko.exchangerates.ui;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:51
 */
public class MainPresenter implements MainScreen.Presenter{

    private MainScreen.View mView;

    @Inject
    MainPresenter(MainScreen.View mView) {
        this.mView = mView;
    }


}
