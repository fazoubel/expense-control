package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ListView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Promotion;

import java.util.List;

public class PromotionsView extends ConstraintLayout{

    private ListView listView;
    private PromotionsViewAdapter adapter;

    public PromotionsView(Context context) {
        super(context);
        init();
    }

    public PromotionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_promotions, this);
        listView = findViewById(R.id.promotionsListView);
    }

    public void setData(List<Promotion> promotions, Activity activity) {
        if(adapter == null) {
            adapter = new PromotionsViewAdapter(promotions, activity);
            listView.setAdapter(adapter);
        }
        else {
            adapter.setUsers(promotions);
            adapter.setActivity(activity);
            adapter.notifyDataSetChanged();
        }
    }

    public Promotion getSelectedPromotion() {
        return adapter.getSelectedPromotion();
    }

    public void clearSelected() {
        if(adapter != null) {
            adapter.clearSelected();
        }
    }
}
