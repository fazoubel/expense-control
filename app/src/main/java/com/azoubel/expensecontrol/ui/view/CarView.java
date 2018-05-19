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
import com.azoubel.expensecontrol.model.User.Car;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.ui.UserPickerActivity;

public class CarView extends ConstraintLayout {

    public static final int CAR_OWNER_PICKER_REQUEST = 2;

    private Car car;

    private Person owner;

    private EditText modelET;
    private EditText yearET;
    private EditText colorET;
    private EditText brandET;
    private EditText plateET;
    private EditText typeET;
    private Button ownerButton;
    private EditText ownerET;

    private Activity intentStarter;

    public CarView(Context context) {
        super(context);
        init();
    }

    public CarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_car, this);
        modelET = findViewById(R.id.model);
        yearET = findViewById(R.id.year);
        colorET = findViewById(R.id.color);
        brandET = findViewById(R.id.brand);
        plateET = findViewById(R.id.plate);
        typeET = findViewById(R.id.type);
        ownerButton = findViewById(R.id.carOwnerButton);
        ownerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentStarter != null) {
                    Intent userPickerActivity = new Intent(intentStarter, UserPickerActivity.class);
                    intentStarter.startActivityForResult(userPickerActivity, CAR_OWNER_PICKER_REQUEST);
                }

            }
        });
        ownerET = findViewById(R.id.carOwner);
    }

    public void fillComponents(Car car) {
        if(car != null) {
            this.car = car;
            modelET.setText(car.getModel());
            yearET.setText("" + car.getYear());
            colorET.setText(car.getColor());
            brandET.setText(car.getBrand());
            plateET.setText("" + car.getPlateNumber());
            typeET.setText(car.getType());
            if(car.getOwner() != null) {
                ownerET.setText(car.getOwner().getFirstName() + " " + car.getOwner().getLastName());
            }
        }
    }

    public Car buildCar() {
        if(car == null) {
            car = new Car();
        }

        car.setModel(modelET.getText().toString());
        if(!TextUtils.isEmpty(yearET.getText().toString())) {
            car.setYear(Integer.parseInt(yearET.getText().toString()));
        }
        car.setColor(colorET.getText().toString());
        car.setBrand(brandET.getText().toString());
        car.setPlateNumber(plateET.getText().toString());
        car.setType(typeET.getText().toString());
        car.setOwner(owner);
        return car;
    }

    public void setIntentStarter(Activity intentStarter) {
        this.intentStarter = intentStarter;
    }

    public void setOwner(Person owner) {
        if(owner != null) {
            this.owner = owner;
            this.ownerET.setText(owner.getFirstName() + " " +owner.getLastName());
        }
    }

}
