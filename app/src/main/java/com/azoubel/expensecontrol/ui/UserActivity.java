package com.azoubel.expensecontrol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.User.Car;
import com.azoubel.expensecontrol.model.User.House;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.Pet;
import com.azoubel.expensecontrol.model.User.User;
import com.azoubel.expensecontrol.ui.view.AddressView;
import com.azoubel.expensecontrol.ui.view.CarView;
import com.azoubel.expensecontrol.ui.view.HouseView;
import com.azoubel.expensecontrol.ui.view.PersonView;
import com.azoubel.expensecontrol.ui.view.PetView;

public class UserActivity extends AbstractActivity {

    private static final int SHOW_PERSON = 0;
    private static final int SHOW_PET = 1;
    private static final int SHOW_HOUSE = 2;
    private static final int SHOW_CAR = 3;

    private RadioGroup chooseUserType;

    private ConstraintLayout userViewContainer;

    private EditText expenseLimitET;
    private Button imageButton;
    private ImageView userImage;
    private AddressView addressView;

    private ConstraintLayout dinamicViewContainer;

    private PersonView personView;
    private PetView petView;
    private HouseView houseView;
    private CarView carView;

    private Button saveButton;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                long userId = intent.getLongExtra("id", -1);
                user = controller.getUser(this, userId);
            }
        }
        init();
    }

    @Override
    protected void init() {

        chooseUserType = findViewById(R.id.chooseUserType);

        if(user != null) {
            chooseUserType.setVisibility(View.GONE);

        }

        userViewContainer = findViewById(R.id.userViewContainer);
        expenseLimitET = userViewContainer.findViewById(R.id.expenseLimit);
        imageButton = userViewContainer.findViewById(R.id.imageButton);
        userImage = userViewContainer.findViewById(R.id.image);
        addressView = userViewContainer.findViewById(R.id.addressView);
        addressView.setIntentStarter(this);
        saveButton = userViewContainer.findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        dinamicViewContainer = userViewContainer.findViewById(R.id.dinamicViewContainer);

        personView = dinamicViewContainer.findViewById(R.id.personView);

        petView = dinamicViewContainer.findViewById(R.id.petView);
        petView.setIntentStarter(this);

        houseView = dinamicViewContainer.findViewById(R.id.houseView);
        houseView.setIntentStarter(this);

        carView = dinamicViewContainer.findViewById(R.id.carView);
        carView.setIntentStarter(this);

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
                petView.fillComponents((Pet) user);
            }
            else if(user instanceof House) {
                showView(SHOW_HOUSE);
                houseView.fillComponents((House) user);
            }
            else {
                showView(SHOW_CAR);
                carView.fillComponents((Car) user);
            }

            expenseLimitET.setText("" + user.getExpectedExpensesValue());

            Address address = user.getAddress();
            if(address != null) {
                addressView.fillAddress(user.getAddress());
            }

            userImage.setImageDrawable(getDrawable(getUserImage(user.getImage())));
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
        else if(petView.getVisibility() == View.VISIBLE) {
            user = petView.buildPet();
        }
        else if(houseView.getVisibility() == View.VISIBLE) {
            user = houseView.buildHouse();
        }
        else if(carView.getVisibility() == View.VISIBLE) {
            user = carView.buildCar();
        }

        String expenseLimit = expenseLimitET.getText().toString();
        if(!TextUtils.isEmpty(expenseLimit)){
            expenseLimit = expenseLimit.replaceAll(",", ".");
            user.setExpectedExpensesValue(Float.parseFloat(expenseLimit));
        }
        else {
            user.setExpectedExpensesValue(0);
        }

        user.setImage(0);

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

        userViewContainer.setVisibility(View.VISIBLE);

        if(toShow == SHOW_PERSON) {
            personView.setVisibility(View.VISIBLE);
            petView.setVisibility(View.GONE);
            houseView.setVisibility(View.GONE);
            carView.setVisibility(View.GONE);
        }
        else if(toShow == SHOW_PET) {
            personView.setVisibility(View.GONE);
            petView.setVisibility(View.VISIBLE);
            houseView.setVisibility(View.GONE);
            carView.setVisibility(View.GONE);
        }
        else if (toShow == SHOW_HOUSE) {
            personView.setVisibility(View.GONE);
            petView.setVisibility(View.GONE);
            houseView.setVisibility(View.VISIBLE);
            carView.setVisibility(View.GONE);
        }
        else if (toShow == SHOW_CAR) {
            personView.setVisibility(View.GONE);
            petView.setVisibility(View.GONE);
            houseView.setVisibility(View.GONE);
            carView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == PetView.PET_OWNER_PICKER_REQUEST && petView != null) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                Person owner = controller.getPerson(this, id);
                petView.setOwner(owner);
            }
            else if(requestCode == HouseView.HOUSE_TENANT_PICKER_REQUEST && houseView != null) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                Person tenant = controller.getPerson(this, id);
                houseView.setTenant(tenant);
            }
            else if(requestCode == CarView.CAR_OWNER_PICKER_REQUEST && carView != null) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                Person owner = controller.getPerson(this, id);
                carView.setOwner(owner);
            }
            else if(requestCode == AddressView.PICK_USER_ADDRESS && addressView != null) {
                long id = Long.parseLong(data.getStringExtra("id"));
                Person owner = controller.getPerson(this, id);
                if(owner != null && owner.getAddress() != null) {
                    if(user != null) {
                        user.setAddress(owner.getAddress());
                    }
                    addressView.fillAddress(owner.getAddress());
                }
            }
        }
    }

    public int getUserImage(int userImage) {
        switch (userImage) {
            case User.IMAGE_BABE: return R.drawable.babe;
            case User.IMAGE_BOY: return R.drawable.boy;
            case User.IMAGE_CAT: return R.drawable.cat;
            case User.IMAGE_DOG: return R.drawable.dog;
            case User.IMAGE_FATHER: return R.drawable.father;
            case User.IMAGE_GIRL: return R.drawable.girl;
            case User.IMAGE_OLD_MAN: return R.drawable.old_man;
            case User.IMAGE_OLD_WOMAN: return R.drawable.old_woman;
            default: return R.drawable.woman;
        }
    }
}
