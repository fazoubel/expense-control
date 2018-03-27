package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.User.User;

import java.util.List;

public class UsersViewAdapter extends BaseAdapter {

    List<User> users;
    private Activity activity;
    private UsersView.UserClickListener listener;

    public UsersViewAdapter(List<User> users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return users.get(i).getUserId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item_user_, viewGroup, false);
        }

        TextView userNameView = view.findViewById(R.id.userItem);
        userNameView.setText(users.get(i).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onUserClicked(getItem(i).getUserId());
                }
            }
        });

        return view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUserClickListener(UsersView.UserClickListener listener) {
        this.listener = listener;
    }
}