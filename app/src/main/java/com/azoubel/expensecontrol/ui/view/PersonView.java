package com.azoubel.expensecontrol.ui.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.User.Person;

import java.util.Date;

public class PersonView extends ConstraintLayout {

    private Person person;

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText nicknameET;
    private EditText phoneNumberET;
    private EditText birthDateET;
    private EditText expenseLimitET;
    private EditText sexET;
    private Button imageButton;

    public PersonView(Context context) {
        super(context);
        init();
    }

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_person, this);
        firstNameET = findViewById(R.id.personFirstName);
        lastNameET = findViewById(R.id.personLastName);
        nicknameET = findViewById(R.id.nickname);
        phoneNumberET = findViewById(R.id.phoneNumber);
        birthDateET = findViewById(R.id.birthDate);
        expenseLimitET = findViewById(R.id.expenseLimit);
        //sexET = findViewById(R.id.sex);
        imageButton = findViewById(R.id.imageButton);
    }

    public void fillComponents(Person person) {
        if(person != null) {
            this.person = person;
            firstNameET.setText(person.getFirstName());
            lastNameET.setText(person.getLastName());
            nicknameET.setText(person.getNickName());
            phoneNumberET.setText(person.getPhoneNumber());
            birthDateET.setText("" + person.getBirthday());
            expenseLimitET.setText("" + person.getExpectedExpensesValue());
        }
    }

    public Person buildPerson() {
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
        return person;
    }

}
