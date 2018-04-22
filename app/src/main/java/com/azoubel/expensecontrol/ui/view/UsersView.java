package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ListView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.User.User;

import java.util.List;

public class UsersView extends ConstraintLayout{

    private ListView listView;
    private UsersViewAdapter adapter;

    public UsersView(Context context) {
        super(context);
        init();
    }

    public UsersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_users, this);
        listView = findViewById(R.id.usersListView);
    }

    public void setData(List<User> users, Activity activity) {
        if(adapter == null) {
            adapter = new UsersViewAdapter(users, activity);
            listView.setAdapter(adapter);
        }
        else {
            adapter.setUsers(users);
            adapter.setActivity(activity);
            adapter.notifyDataSetChanged();
        }
    }

    public User getSelectedUser() {
        return adapter.getSelectedUser();
    }

    public void clearSelected() {
        if(adapter != null) {
            adapter.clearSelected();
        }
    }

}
