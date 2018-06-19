package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Promotion;

import java.util.List;

public class PromotionsViewAdapter extends BaseAdapter {

    List<Promotion> promotions;
    private Activity activity;
    private Promotion selectedPromotion;
    private View selectedView;

    public PromotionsViewAdapter(List<Promotion> promotions, Activity activity) {
        this.promotions = promotions;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return promotions.size();
    }

    @Override
    public Promotion getItem(int position) {
        return promotions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return promotions.get(position).getPromotionId();
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.list_item_image);
        imageView.setImageDrawable(activity.getDrawable(R.drawable.store));

        TextView userDescription = view.findViewById(R.id.listItemDescription);
        userDescription.setText(promotions.get(i).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.BLUE);
                if(selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
                selectedView = view;
                selectedPromotion = getItem(i);
            }
        });

        return view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setUsers(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion getSelectedPromotion() {
        return selectedPromotion;
    }

    public void clearSelected() {
        selectedPromotion = null;
        if(selectedView != null) {
            selectedView.setBackgroundColor(Color.TRANSPARENT);
            selectedView = null;
        }
    }

}
