package com.borysenko.exchangerates.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.borysenko.exchangerates.R;
import com.borysenko.exchangerates.dagger.DaggerMainScreenComponent;
import com.borysenko.exchangerates.dagger.MainScreenModule;
import com.borysenko.exchangerates.model.ExchangeRates;
import com.borysenko.exchangerates.model.Rate;


import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreen.View {

    @Inject
    MainPresenter mainPresenter;

    TableLayout mPbTable;
    TableLayout mNbTable;
    Locale ruLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainScreenComponent.builder()
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        mPbTable = findViewById(R.id.pb_table);
        mNbTable = findViewById(R.id.nb_table);
        ruLocale = new Locale("ru");

        mainPresenter.loadRates();
    }

    @Override
    public void fillPBTable(ExchangeRates exRates) {

        Rate[] rates = exRates.getRates();

        mPbTable.setStretchAllColumns(true);

        for (Rate rate:rates) {

            if(rate.getPurchaseRate()!=0 && rate.getCurrency()!=null) {
                TableRow tr = new TableRow(MainActivity.this);

                //currency
                TextView mCurrency = new TextView(MainActivity.this);
                mCurrency.setText(rate.getCurrency());
                tr.addView(mCurrency, 0);

                //purchase
                TextView mPbPurchase = new TextView(MainActivity.this);
                mPbPurchase.setText(String.valueOf(rate.getPurchaseRate()));
                tr.addView(mPbPurchase, 1);

                //sale
                TextView mPbSale = new TextView(MainActivity.this);
                mPbSale.setText(String.valueOf(rate.getSaleRate()));
                tr.addView(mPbSale, 2);

                mPbTable.addView(tr);
            }
        }
    }

    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void fillNBTable(ExchangeRates exRates) {

        Rate[] rates = exRates.getRates();

        mNbTable.setStretchAllColumns(true);

        for (Rate rate:rates) {

            if (rate.getCurrency()!=null) {

                TableRow tr = new TableRow(MainActivity.this);

                //currency name
                TextView mCurrency = new TextView(MainActivity.this);
                mCurrency.setText(rate.getCurrency().equals("BYN") ? "Белорусский рубль" :
                        Currency.getInstance(rate.getCurrency()).getDisplayName(ruLocale));
                tr.addView(mCurrency, 0);

                //NB exchange rate
                LinearLayout container = new LinearLayout(MainActivity.this);
                container.setOrientation(LinearLayout.VERTICAL);

                TextView mNbPurchase = new TextView(MainActivity.this);
                TextView mCurCurrency = new TextView(MainActivity.this);
                if(rate.getNBRate()>1.0f) {
                    mNbPurchase.setText(String.format("%.2f%s", rate.getNBRate(),
                            exRates.getBaseCurrency()));
                    mCurCurrency.setText(String.format("1%s", rate.getCurrency()));
                } else {
                    mNbPurchase.setText(String.format("%.2f%s", rate.getNBRate()*100,
                            exRates.getBaseCurrency()));
                    mCurCurrency.setText(String.format("100%s", rate.getCurrency()));
                }

                container.addView(mNbPurchase);
                container.addView(mCurCurrency);

                tr.addView(container, 1);

                mNbTable.addView(tr);
            }
        }

    }
}
