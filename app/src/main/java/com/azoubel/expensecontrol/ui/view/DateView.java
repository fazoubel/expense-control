package com.azoubel.expensecontrol.ui.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;

import java.util.Date;

public class DateView extends ConstraintLayout{

    private TextView titleTV;
    private EditText dayET;
    private EditText monthET;
    private EditText yearET;

    public DateView(Context context) {
        super(context);
        init();
    }

    public DateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_date, this);
        titleTV = findViewById(R.id.title);
        dayET = findViewById(R.id.day);
        monthET = findViewById(R.id.month);
        yearET = findViewById(R.id.year);
    }

    public void fillComponents(Date date, String title) {
        titleTV.setText(title);
        if(date != null) {
            int day = date.getDate();
            int month = date.getMonth();
            int year = 1900 + date.getYear();
            dayET.setText("" + day);
            monthET.setText("" + (month + 1));
            yearET.setText("" + year);
        }
    }

    public Date buildDate() {
        Date date = new Date();
        int day = 0;
        int month = 0;
        int year = 0;

        if(!TextUtils.isEmpty(dayET.getText().toString())) {
            day = Integer.parseInt(dayET.getText().toString());
        }
        if(!TextUtils.isEmpty(monthET.getText().toString())) {
            month = Integer.parseInt(monthET.getText().toString()) - 1;
        }
        if(!TextUtils.isEmpty(yearET.getText().toString())) {
            year = Integer.parseInt(yearET.getText().toString()) - 1900;
        }

        if( day == 0 || month == 0 || year == 0) {
            return null;
        } else {
            date.setDate(day);
            date.setMonth(month);
            date.setYear(year);

            return date;
        }

    }
}
