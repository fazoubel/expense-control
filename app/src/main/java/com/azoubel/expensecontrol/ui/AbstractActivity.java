package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.azoubel.expensecontrol.controller.Controller;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.ui.view.AddressView;

public abstract class AbstractActivity extends Activity {

    protected abstract int getContentViewXML();

    protected Controller controller;

    protected abstract void init();

    protected abstract void fillComponents();

    protected abstract void save();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewXML());
        controller = new Controller();
    }

}
