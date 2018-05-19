package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.User.User;

import java.util.List;

public class UsersViewAdapter extends BaseAdapter {

    List<User> users;
    private Activity activity;
    private User selectedUser;
    private View selectedView;

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
            view = activity.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.list_item_image);
        imageView.setImageDrawable(activity.getDrawable(getUserImage(users.get(i).getImage())));

        TextView userDescription = view.findViewById(R.id.listItemDescription);
        userDescription.setText(users.get(i).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.BLUE);
                if(selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
                selectedView = view;
                selectedUser = getItem(i);
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

    public User getSelectedUser() {
        return selectedUser;
    }

    public void clearSelected() {
        selectedUser = null;
        if(selectedView != null) {
            selectedView.setBackgroundColor(Color.TRANSPARENT);
            selectedView = null;
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