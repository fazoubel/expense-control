package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.azoubel.expensecontrol.controller.Controller;

public abstract class AbstractActivity extends Activity{

    protected abstract int getContentViewXML();

    protected Controller controller;

    protected abstract void init();

    protected abstract void save();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewXML());
        controller = new Controller();
    }
}
