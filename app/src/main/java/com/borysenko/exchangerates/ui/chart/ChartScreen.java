package com.borysenko.exchangerates.ui.chart;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 00:59
 */
public interface ChartScreen {
    interface View {

    }

    interface Presenter {

        void loadChartData(List<String> dateRange);
    }
}
