package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.User.User;

import java.util.List;

public class ExpensesViewAdapter extends BaseAdapter {

    private List<Expense> expenses;
    private Activity activity;
    private View selectedView;
    private Expense selectedExpense;

    public ExpensesViewAdapter(List<Expense> expenses, Activity activity) {
        this.expenses = expenses;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return expenses.size();
    }

    @Override
    public Expense getItem(int i) {
        return expenses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.list_item_image);
        if(expenses.get(i).getBuyer() != null) {
            imageView.setImageDrawable(activity.getDrawable(getUserImage(expenses.get(i).getBuyer().getImage())));
        }

        TextView expenseDescription = view.findViewById(R.id.listItemDescription);
        expenseDescription.setText(expenses.get(i).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.BLUE);
                if(selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
                selectedView = view;
                selectedExpense = getItem(i);
            }
        });

        return view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Expense getSelectedExpense() {
        return selectedExpense;
    }

    public void clearSelected() {
        selectedExpense = null;
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
