package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.User.User;

import java.util.List;

public class CreditCardAdapter extends BaseAdapter {

    List<CreditCard> creditcards;
    private Activity activity;
    private CreditCard selectedUser;
    private View selectedView;

    public CreditCardAdapter(List<CreditCard> creditcards, Activity activity) {
        this.creditcards = creditcards;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return creditcards.size();
    }

    @Override
    public CreditCard getItem(int i) {
        return creditcards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return creditcards.get(i).getNumber().hashCode();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
        }

        TextView userDescription = view.findViewById(R.id.listItemDescription);
        userDescription.setText(creditcards.get(i).toString());

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

    public void setcreditcards(List<CreditCard> creditcards) {
        this.creditcards = creditcards;
    }

    public CreditCard getSelectedCreditCard() {
        return selectedUser;
    }

    public void clearSelected() {
        selectedUser = null;
        if(selectedView != null) {
            selectedView.setBackgroundColor(Color.TRANSPARENT);
            selectedView = null;
        }
    }
}