package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.User.User;
import com.azoubel.expensecontrol.ui.view.UsersView;

import java.util.List;

public class UserPickerActivity extends AbstractActivity{

    private UsersView usersView;
    private Button pickerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_user_picker;
    }

    @Override
    protected void init() {
        usersView = findViewById(R.id.users_view_layout);
        pickerButton = findViewById(R.id.pickerButton);
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        List<User> users = controller.loadAllPersons(this);
        usersView.setData(users, this);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    @Override
    protected void save() {
        User selectedUser = usersView.getSelectedUser();
        if(selectedUser != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", ""+selectedUser.getUserId());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
