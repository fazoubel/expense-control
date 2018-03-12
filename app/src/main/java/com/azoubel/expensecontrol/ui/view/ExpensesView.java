package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ListView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Expense;

import java.util.List;

public class ExpensesView extends ConstraintLayout {

    private ListView listView;
    private ExpensesViewAdapter adapter;

    public ExpensesView(Context context) {
        super(context);
        init();
    }

    public ExpensesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_expenses, this);
        listView = findViewById(R.id.expensesListView);
    }

    public void setData(List<Expense> expenses, Activity activity) {
        if(adapter == null) {
            adapter = new ExpensesViewAdapter(expenses, activity);
            listView.setAdapter(adapter);
        }
        else {
            adapter.setExpenses(expenses);
            adapter.setActivity(activity);
            adapter.notifyDataSetChanged();
        }
    }
}
