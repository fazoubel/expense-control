package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Store;

import java.util.List;

public class StoreViewAdapter extends BaseAdapter {

    private List<Store> stores;
    private Activity activity;
    private View selectedView;
    private Store selectedStore;
    private StoreView.StoreClickListener listener;

    public StoreViewAdapter(List<Store> stores, Activity activity) {
        this.stores = stores;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public Store getItem(int i) {
        return stores.get(i);
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

        TextView storeDescription = view.findViewById(R.id.listItemDescription);
        storeDescription.setText(stores.get(i).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            view.setBackgroundColor(Color.BLUE);
            if(selectedView != null) {
                selectedView.setBackgroundColor(Color.TRANSPARENT);
            }
            selectedView = view;
            selectedStore = getItem(i);
            if(listener != null) {
                listener.onStoreClicked(getItem(i).getStoreId());
            }

            }
        });

        return view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void setPaymentClickListener(StoreView.StoreClickListener listener) {
        this.listener = listener;
    }

    public Store getSelectedStore() {
        return selectedStore;
    }

    public void clearSelected() {
        selectedStore = null;
        if(selectedView != null) {
            selectedView.setBackgroundColor(Color.TRANSPARENT);
            selectedView = null;
        }
    }

}
