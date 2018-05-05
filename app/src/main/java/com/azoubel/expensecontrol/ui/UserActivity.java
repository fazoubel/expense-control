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
            String firstName = firstNameET.getText().toString();
            String lastName = lastNameET.getText().toString();
            String nickname = nicknameET.getText().toString();
            String phoneNumber = phoneNumberET.getText().toString();
            String birthDate = birthDateET.getText().toString();
            String expenseLimit = expenseLimitET.getText().toString();
            if(!TextUtils.isEmpty(expenseLimit)){
                expenseLimit = expenseLimit.replaceAll(",", ".");
            }

            person = new Person();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setNickName(nickname);
            person.setPhoneNumber(phoneNumber);
        /*if(!TextUtils.isEmpty(birthDate)){
            Date date = new Date();
            date.setDate();
            person.setBirthday();
        }*/
            person.setBirthday(new Date());
            person.setSex(0);
            person.setExpectedExpensesValue(Float.parseFloat(expenseLimit));
            person.setImage(0);
            Address address = addressView.getAddress();
            person.setAddress(address);
            controller.addPerson(this, person);
        }
        else {
            //update
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
}
