package com.borysenko.exchangerates.ui.chart;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.borysenko.exchangerates.R;
import com.borysenko.exchangerates.dagger.ChartScreenModule;
import com.borysenko.exchangerates.dagger.DaggerChartScreenComponent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/12/18
 * Time: 00:58
 */
public class ChartActivity extends AppCompatActivity implements ChartScreen.View  {

    @Inject
    ChartPresenter chartPresenter;

    TextView mFirstDate;
    TextView mSecondDate;
    DatePickerDialog.OnDateSetListener mFirstDateSetListener;
    DatePickerDialog.OnDateSetListener mSecondDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        DaggerChartScreenComponent.builder()
                .chartScreenModule(new ChartScreenModule(this))
                .build().inject(this);

        mFirstDate = findViewById(R.id.first_date);
        mSecondDate = findViewById(R.id.second_date);

        //Start Date DatePicker and End Date DatePicker
        //------------------------------------------------------------------------------------------
        mFirstDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChartActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mFirstDateSetListener,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mFirstDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "." + (month + 1) + "." + year;
                mFirstDate.setText(date);
            }
        };

        mSecondDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ChartActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mSecondDateSetListener,
                        year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mSecondDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "." + (month + 1) + "." + year;
                mSecondDate.setText(date);


                //getting data from privat bank api via multiple retrofit requests
                // no charts developed
                try {
                    //just load data(for each day) and save it to the List
                    chartPresenter.loadChartData(getDaysBetweenDates(mFirstDate.getText().toString(),
                            mSecondDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        //------------------------------------------------------------------------------------------

    }

    @SuppressLint("SimpleDateFormat")
    public static List<String> getDaysBetweenDates(String startDate, String endDate)
            throws ParseException {

        //getting all dates between the other two dates
        //and saving it to a list
        List<String> dates = new ArrayList<>();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(startDate));

        while (calendar.getTime().before(new SimpleDateFormat("dd.MM.yyyy").parse(endDate)))
        {
            Date result = calendar.getTime();
            dates.add(df.format(result));
            calendar.add(Calendar.DATE, 1);
        }
        dates.add(endDate);
        return dates;
    }

}
