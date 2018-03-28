package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.azoubel.expensecontrol.controller.Controller;

public abstract class GeneralActivity extends Activity{

    protected abstract int getContentViewXML();

    protected Controller controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewXML());
        controller = new Controller();
    }
}
