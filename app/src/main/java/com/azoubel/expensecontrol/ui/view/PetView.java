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
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.Pet;
import com.azoubel.expensecontrol.ui.UserPickerActivity;

public class PetView extends ConstraintLayout {

    public static final int PET_OWNER_PICKER_REQUEST = 0;

    private Pet pet;

    private Person owner;

    private EditText petNameET;
    private EditText petAgeET;
    private EditText nicknameET;
    private EditText speciesET;
    private EditText breedET;
    private EditText sexET;
    private Button ownerButton;
    private EditText ownerET;

    private Activity intentStarter;

    public PetView(Context context) {
        super(context);
        init();
    }

    public PetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_pet, this);
        petNameET = findViewById(R.id.petName);
        petAgeET = findViewById(R.id.petAge);
        nicknameET = findViewById(R.id.nickname);
        speciesET = findViewById(R.id.species);
        breedET = findViewById(R.id.breed);
        sexET = findViewById(R.id.petSex);
        ownerButton = findViewById(R.id.petOwnerButton);
        ownerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intentStarter != null) {
                    Intent userPickerActivity = new Intent(intentStarter, UserPickerActivity.class);
                    intentStarter.startActivityForResult(userPickerActivity, PET_OWNER_PICKER_REQUEST);
                }

            }
        });
        ownerET = findViewById(R.id.petOwner);
    }

    public void fillComponents(Pet pet) {
        if(pet != null) {
            this.pet = pet;
            petNameET.setText(pet.getName());
            petAgeET.setText("" + pet.getAge());
            nicknameET.setText(pet.getNickName());
            speciesET.setText(pet.getSpecies());
            breedET.setText("" + pet.getBreed());
            sexET.setText(pet.getSex());
            if(pet.getOwner() != null) {
                ownerET.setText(pet.getOwner().getFirstName() + " " + pet.getOwner().getLastName());
            }
        }
    }

    public Pet buildPet() {
        if(pet == null) {
            pet = new Pet();
        }

        pet.setName(petNameET.getText().toString());
        if(!TextUtils.isEmpty(petAgeET.getText().toString())) {
            pet.setAge(Integer.parseInt(petAgeET.getText().toString()));
        }
        pet.setNickName(nicknameET.getText().toString());
        pet.setSpecies(speciesET.getText().toString());
        pet.setBreed(breedET.getText().toString());
        pet.setSex(sexET.getText().toString());
        pet.setOwner(owner);
        return pet;
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
