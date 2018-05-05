package com.azoubel.expensecontrol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.ui.view.AddressView;

import java.util.Date;

public class UserActivity extends AbstractActivity {

    private AddressView addressView;
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText nicknameET;
    private EditText phoneNumberET;
    private EditText birthDateET;
    private EditText expenseLimitET;
    private EditText sexET;
    private Button imageButton;
    private Button saveButton;

    private Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                int personID = intent.getIntExtra("id", -1);
                person = controller.getPerson(this, personID);
            }
        }
        init();
    }

    @Override
    protected void init() {
        addressView = findViewById(R.id.storeAddressView);
        addressView.setAddressSearcher(this);
        firstNameET = findViewById(R.id.storeName);
        lastNameET = findViewById(R.id.site);
        nicknameET = findViewById(R.id.description);
        phoneNumberET = findViewById(R.id.productType);
        birthDateET = findViewById(R.id.storePhoneNumber);
        expenseLimitET = findViewById(R.id.storeEmail);
        //sexET = findViewById(R.id.sex);
        imageButton = findViewById(R.id.managerName);
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        fillComponents();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_user;
    }

    @Override
    protected void save() {
        if(person == null) {
            buildPerson();
            controller.addPerson(this, person);
        }
        else {
            buildPerson();
            controller.updatePerson(this, person);
        }

        finish();
    }

    @Override
    public void fillComponents() {
        if(person != null) {
            firstNameET.setText(person.getFirstName());
            lastNameET.setText(person.getLastName());
            nicknameET.setText(person.getNickName());
            phoneNumberET.setText(person.getPhoneNumber());
            birthDateET.setText("" + person.getBirthday());
            expenseLimitET.setText("" + person.getExpectedExpensesValue());
            Address address = person.getAddress();
            if(address != null) {
                addressView.fillAddress(person.getAddress());
            }
        }
    }

    private void buildPerson() {
        if(person == null) {
            person = new Person();
        }
        String expenseLimit = expenseLimitET.getText().toString();
        if(!TextUtils.isEmpty(expenseLimit)){
            expenseLimit = expenseLimit.replaceAll(",", ".");
        }
        person.setFirstName(firstNameET.getText().toString());
        person.setLastName(lastNameET.getText().toString());
        person.setNickName(nicknameET.getText().toString());
        person.setPhoneNumber(phoneNumberET.getText().toString());
        person.setBirthday(new Date());//birthDateET.getText().toString());
        person.setSex(0); //sexET.getText().toString();
        person.setExpectedExpensesValue(Float.parseFloat(expenseLimit));
        person.setImage(0);
        Address address = addressView.getAddress();
        if(person.getAddress() != null) {
            address.setAddressId(person.getAddress().getAddressId());
        }
        person.setAddress(address);
    }
}
