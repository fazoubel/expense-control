package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.ui.view.StoreView;

import java.util.List;

public class StorePickerActivity extends AbstractActivity{

    private StoreView storeView;
    private Button pickerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_store_picker;
    }

    @Override
    protected void init() {
        storeView = findViewById(R.id.stores_view_layout);
        pickerButton = findViewById(R.id.pickerButton);
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        List<Store> stores = controller.getAllStores(this);
        storeView.setData(stores, this);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    @Override
    protected void save() {
        Store selectedStore = storeView.getSelectedStore();
        if(selectedStore != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", ""+selectedStore.getStoreId());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
