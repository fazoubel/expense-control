package com.azoubel.expensecontrol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.User.House;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.Pet;
import com.azoubel.expensecontrol.model.User.User;
import com.azoubel.expensecontrol.ui.view.AddressView;
import com.azoubel.expensecontrol.ui.view.PersonView;

public class UserActivity extends AbstractActivity {

    private static final int SHOW_PERSON = 0;
    private static final int SHOW_PET = 1;
    private static final int SHOW_HOUSE = 2;
    private static final int SHOW_CAR = 3;

    private Button saveButton;
    private PersonView personView;
    private AddressView addressView;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                int personID = intent.getIntExtra("id", -1);
                user = controller.getUser(this, personID);
            }
        }
        init();
    }

    @Override
    protected void init() {
        personView = findViewById(R.id.personView);
        addressView = findViewById(R.id.addressView);
        addressView.setAddressSearcher(this);
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
    public void fillComponents() {

        if(user != null) {

            if(user instanceof Person) {
                showView(SHOW_PERSON);
                personView.fillComponents((Person) user);
            }
            else if (user instanceof Pet) {
                showView(SHOW_PET);
            }
            else if(user instanceof House) {
                showView(SHOW_HOUSE);
            }
            else {
                showView(SHOW_CAR);
            }

            Address address = user.getAddress();
            if(address != null) {
                addressView.fillAddress(user.getAddress());
            }
        }
    }

    @Override
    protected void save() {

        boolean isNewUser = (user == null);
        buildUser();

        if(isNewUser) {
            controller.addUser(this, user);
        }
        else {
            controller.updateUser(this, user);
        }

        finish();
    }

    private void buildUser() {

        if(personView.getVisibility() == View.VISIBLE) {
            user = personView.buildPerson();
        }

        Address address = addressView.getAddress();
        if(user.getAddress() != null) {
            address.setAddressId(user.getAddress().getAddressId());
        }
        user.setAddress(address);
    }

    public void onRadioButtonClicked(View view){

        boolean checked = ((RadioButton) view).isChecked();

        if(checked) {
            if(view.getId() == R.id.person) {
                showView(SHOW_PERSON);
            }
            else if (view.getId() == R.id.pet) {
                showView(SHOW_PET);
            }
            else if (view.getId() == R.id.house) {
                showView(SHOW_HOUSE);
            }
            else {
                showView(SHOW_CAR);
            }
        }

    }

    private void showView(int toShow) {
        addressView.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.VISIBLE);
        if(toShow == SHOW_PERSON) {
            personView.setVisibility(View.VISIBLE);
        }
    }

}
