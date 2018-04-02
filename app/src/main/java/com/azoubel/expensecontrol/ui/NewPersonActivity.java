package com.azoubel.expensecontrol.ui;

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

public class NewPersonActivity extends GeneralActivity implements AddressView.AddressSearcher{

    private AddressView addressView;
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText nicknameET;
    private EditText phoneNumberET;
    private EditText birthDateET;
    private EditText expenseLimitET;
    private Button imageButton;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        addressView = findViewById(R.id.addressView);
        addressView.setAddressSearcher(this);
        firstNameET = findViewById(R.id.firstName);
        lastNameET = findViewById(R.id.lastName);
        nicknameET = findViewById(R.id.nickname);
        phoneNumberET = findViewById(R.id.phoneNumber);
        birthDateET = findViewById(R.id.birthDate);
        expenseLimitET = findViewById(R.id.expenseLimit);
        imageButton = findViewById(R.id.image);
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePerson();
            }
        });
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_new_person;
    }

    private void savePerson() {

        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String nickname = nicknameET.getText().toString();
        String phoneNumber = phoneNumberET.getText().toString();
        String birthDate = birthDateET.getText().toString();
        String expenseLimit = expenseLimitET.getText().toString();
        if(!TextUtils.isEmpty(expenseLimit)){
            expenseLimit = expenseLimit.replaceAll(",", ".");
        }

        Address address = addressView.getAddress();
        Person person = new Person();
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

        controller.addPerson(this, firstName, lastName, nickname, phoneNumber,new Date().getTime(),  0, Float.parseFloat(expenseLimit),
                0, address);
        finish();
    }

    @Override
    public Address findAddress(String street, int number, String neiborhood) {
        return controller.findAddress(this, street, number, neiborhood);
    }
}
