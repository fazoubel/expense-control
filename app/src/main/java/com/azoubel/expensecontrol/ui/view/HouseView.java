package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.User.House;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.ui.UserPickerActivity;

public class HouseView extends ConstraintLayout {

    public static final int HOUSE_TENANT_PICKER_REQUEST = 1;

    private House house;

    private Person tenant;

    private EditText typeET;
    private EditText descriptionET;
    private EditText areaET;
    private EditText bedroomsET;
    private EditText garagesET;
    private EditText isRentET;
    private Button tenantButton;
    private EditText tentantET;

    private Activity intentStarter;

    public HouseView(Context context) {
        super(context);
        init();
    }

    public HouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_house, this);
        typeET = findViewById(R.id.type);
        descriptionET = findViewById(R.id.description);
        areaET = findViewById(R.id.area);
        bedroomsET = findViewById(R.id.bedrooms);
        garagesET = findViewById(R.id.garages);
        isRentET = findViewById(R.id.isRent);
        tenantButton = findViewById(R.id.tenantButton);
        tenantButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentStarter != null) {
                    Intent userPickerActivity = new Intent(intentStarter, UserPickerActivity.class);
                    intentStarter.startActivityForResult(userPickerActivity, HOUSE_TENANT_PICKER_REQUEST);
                }

            }
        });
        tentantET = findViewById(R.id.tenant);
    }

    public void fillComponents(House house) {
        if(house != null) {
            this.house = house;
            typeET.setText(house.getType());
            descriptionET.setText(house.getDescription());
            areaET.setText("" + house.getArea());
            bedroomsET.setText("" + house.getRooms());
            garagesET.setText("" + house.getGarages());
            isRentET.setText(house.getIsRented());
            if(house.getTenant() != null) {
                tentantET.setText(house.getTenant().getFirstName() + " " + house.getTenant().getLastName());
            }
        }
    }

    public House buildHouse() {
        if(house == null) {
            house = new House();
        }

        house.setType(typeET.getText().toString());

        if(!TextUtils.isEmpty(descriptionET.getText().toString())) {
            house.setDescription(descriptionET.getText().toString());
        }
        if(!TextUtils.isEmpty(areaET.getText().toString())) {
            house.setArea(Integer.parseInt(areaET.getText().toString()));
        }

        if(!TextUtils.isEmpty(bedroomsET.getText().toString())) {
            house.setRooms(Integer.parseInt(bedroomsET.getText().toString()));
        }

        if(!TextUtils.isEmpty(garagesET.getText().toString())) {
            house.setGarages(Integer.parseInt(garagesET.getText().toString()));
        }

        house.setIsRented(isRentET.getText().toString());
        house.setTenant(tenant);

        return house;
    }

    public void setIntentStarter(Activity intentStarter) {
        this.intentStarter = intentStarter;
    }

    public void setTenant(Person tenant) {
        if(tenant != null) {
            this.tenant = tenant;
            this.tentantET.setText(tenant.getFirstName() + " " +tenant.getLastName());
        }
    }

}
