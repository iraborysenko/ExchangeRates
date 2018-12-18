package com.borysenko.exchangerates.ui.main;


import com.borysenko.exchangerates.model.ExchangeRates;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/12/18
 * Time: 14:51
 */
public interface MainScreen {
    interface View {

        void showErrorToast();

        void clearTables();

        void setProgressBarVisible();

        void setProgressBarInvisible();

        void fillPBTable(ExchangeRates rates);

        void fillNBTable(ExchangeRates rates);
    }

    interface Presenter {

        void loadRates(String date);

    }
}
