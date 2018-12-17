package com.borysenko.exchangerates.ui.main;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.borysenko.exchangerates.R;
import com.borysenko.exchangerates.dagger.DaggerMainScreenComponent;
import com.borysenko.exchangerates.dagger.MainScreenModule;
import com.borysenko.exchangerates.model.ExchangeRates;
import com.borysenko.exchangerates.model.Rate;

import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreen.View {

    @Inject
    MainPresenter mainPresenter;

    TableLayout mPbTable;
    TableLayout mNbTable;
    Locale ruLocale;

    TextView mSelectedDate;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainScreenComponent.builder()
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        mPbTable = findViewById(R.id.pb_table);
        mNbTable = findViewById(R.id.nb_table);
        mSelectedDate = findViewById(R.id.slDate);
        ruLocale = new Locale("ru");

        mSelectedDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "." + (month + 1) + "." + year;
                mSelectedDate.setText(date);
                mainPresenter.loadRates(date);
            }
        };

        String currentDate = getCurrentDate();
        mainPresenter.loadRates(currentDate);
        mSelectedDate.setText(currentDate);
    }

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return (calendar.get(Calendar.DAY_OF_MONTH)-1) + "."
                + (calendar.get(Calendar.MONTH) + 1) + "."
                + calendar.get(Calendar.YEAR);
    }

    @Override
    public void fillPBTable(ExchangeRates exRates) {

        final NestedScrollView mNbScroll = findViewById(R.id.nb_scroll);

        Rate[] rates = exRates.getRates();

        mPbTable.setStretchAllColumns(true);

        for (Rate rate:rates) {

            if(rate.getPurchaseRate()!=0 && rate.getCurrency()!=null) {
                TableRow tr = new TableRow(MainActivity.this);

                final String selectedCur = rate.getCurrency();

                //currency
                TextView mCurrency = new TextView(MainActivity.this);
                mCurrency.setText(rate.getCurrency());
                mCurrency.setTextSize(18);
                tr.addView(mCurrency, 0);

                //purchase
                TextView mPbPurchase = new TextView(MainActivity.this);
                mPbPurchase.setText(String.valueOf(rate.getPurchaseRate()));
                mPbPurchase.setTextSize(18);
                tr.addView(mPbPurchase, 1);

                //sale
                TextView mPbSale = new TextView(MainActivity.this);
                mPbSale.setText(String.valueOf(rate.getSaleRate()));
                mPbSale.setTextSize(18);
                tr.addView(mPbSale, 2);

                tr.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        clearTablesRowBackGround();
                        v.setBackgroundColor(getResources().getColor(R.color.colorDatePicker));

                        final View child = mNbTable.getChildAt(findAppropriateNBRow(selectedCur));
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                mNbScroll.smoothScrollTo(0, child.getBottom());
                                child.setBackgroundColor(getResources().getColor(R.color.colorDatePicker));
                            }
                        });
                    }
                });

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

                TableLayout.LayoutParams lp =
                        new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.MATCH_PARENT);

                lp.setMargins(0,0,0,8);
                tr.setLayoutParams(lp);

                //currency name
                TextView mCurrency = new TextView(MainActivity.this);
                mCurrency.setTextSize(16);
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

                mCurCurrency.setTextSize(14);
                mCurCurrency.setTextColor(getResources().getColor(android.R.color.darker_gray));
                mNbPurchase.setTextSize(16);

                container.addView(mNbPurchase);
                container.addView(mCurCurrency);

                tr.addView(container, 1);

                tr.setTag(rate.getCurrency());

                mNbTable.addView(tr);
            }
        }
    }

    int findAppropriateNBRow(String selectedCurrency) {
        int selectedIndex = 0;
        for(int i = 0, j = mNbTable.getChildCount(); i < j; i++) {
            if(mNbTable.getChildAt(i).getTag().equals(selectedCurrency)) {
                selectedIndex = i;
            }
        }
        return selectedIndex;
    }

    void clearTablesRowBackGround() {
        for(int i = 0, j = mNbTable.getChildCount(); i < j; i++) {
            mNbTable.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorBackground));
        }

        for(int i = 0, j = mPbTable.getChildCount(); i < j; i++) {
            mPbTable.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colorBackground));
        }
    }
}
