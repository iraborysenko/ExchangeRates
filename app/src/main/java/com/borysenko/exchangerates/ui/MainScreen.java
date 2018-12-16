package com.borysenko.exchangerates.ui;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:51
 */
public interface MainScreen {
    interface View {

    }

    interface Presenter {

        void loadRates();
    }
}
