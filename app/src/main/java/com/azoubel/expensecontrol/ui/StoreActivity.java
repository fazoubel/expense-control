package com.azoubel.expensecontrol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.ui.view.AddressView;

public class StoreActivity extends AbstractActivity{

    private EditText nameET;
    private EditText siteET;
    private EditText descriptionET;
    private EditText productTypeET;
    private EditText phoneNumberET;
    private EditText emailET;
    private EditText managerNameET;
    private EditText managerPhoneNumberET;
    private EditText managerEmailET;
    private AddressView addressView;
    private Button saveBT;
    private Store store;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("id")) {
            store = controller.getStore(this, intent.getIntExtra("id", -1));
        }
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_store;
    }

    @Override
    protected void init() {
        nameET = findViewById(R.id.storeName);
        siteET = findViewById(R.id.site);
        descriptionET = findViewById(R.id.description);
        productTypeET = findViewById(R.id.productType);
        phoneNumberET = findViewById(R.id.storePhoneNumber);
        emailET = findViewById(R.id.storeEmail);
        managerNameET = findViewById(R.id.managerName);
        managerPhoneNumberET = findViewById(R.id.managerPhoneNumber);
        managerEmailET = findViewById(R.id.managerEmail);
        addressView = findViewById(R.id.storeAddressView);
        addressView.setAddressSearcher(this);
        saveBT = findViewById(R.id.save);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        if(store != null) {
            nameET.setText(store.getStoreName());
            siteET.setText(store.getSite());
            descriptionET.setText(store.getDescription());
            productTypeET.setText(store.getProductType());
            phoneNumberET.setText(store.getPhoneNumber());
            emailET.setText(store.getEmail());
            managerNameET.setText(store.getManagerName());
            managerPhoneNumberET.setText(store.getManagerPhoneNumber());
            managerEmailET.setText(store.getManagerEmail());
            addressView.fillAddress(store.getAddress());
        }
    }

    @Override
    protected void save() {
        if(store == null) {
            store = new Store();
            store.setStoreName(nameET.getText().toString());
            store.setSite(siteET.getText().toString());
            store.setDescription(descriptionET.getText().toString());
            store.setProductType(productTypeET.getText().toString());
            store.setPhoneNumber(phoneNumberET.getText().toString());
            store.setEmail(emailET.getText().toString());
            store.setManagerName(managerNameET.getText().toString());
            store.setManagerPhoneNumber(managerPhoneNumberET.getText().toString());
            store.setManagerEmail(managerEmailET.getText().toString());
            store.setAddress(addressView.getAddress());
            controller.addStore(this, store);
        }
        else{
            //update store
        }

        finish();
    }
}
