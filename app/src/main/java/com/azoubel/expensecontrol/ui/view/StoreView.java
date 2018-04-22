package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ListView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Store;

import java.util.List;

public class StoreView extends ConstraintLayout {

    public interface StoreClickListener {
        void onStoreClicked(int storeId);
    }

    private ListView listView;
    private StoreViewAdapter adapter;

    public StoreView(Context context) {
        super(context);
        init();
    }

    public StoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_stores, this);
        listView = findViewById(R.id.storesListView);
    }

    public void setData(List<Store> stores, Activity activity) {
        if(adapter == null) {
            adapter = new StoreViewAdapter(stores, activity);
            listView.setAdapter(adapter);
        }
        else {
            adapter.setStores(stores);
            adapter.setActivity(activity);
            adapter.notifyDataSetChanged();
        }
    }

    public void setStoreClickListener(StoreView.StoreClickListener listener) {
        if(adapter != null) {
            adapter.setPaymentClickListener(listener);
        }
    }

    public Store getSelectedStore() {
        return adapter.getSelectedStore();
    }

    public void clearSelected() {
        if(adapter != null) {
            adapter.clearSelected();
        }
    }
}
