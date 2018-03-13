package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Payment;

import java.util.List;

public class PaymentsViewAdapter extends BaseAdapter {

    private List<Payment> payments;
    private Activity activity;

    public PaymentsViewAdapter(List<Payment> payments, Activity activity) {
        this.payments = payments;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return payments.size();
    }

    @Override
    public Payment getItem(int i) {
        return payments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item_payment, viewGroup, false);
        }

        TextView userNameView = view.findViewById(R.id.expenseItem);
        userNameView.setText(payments.get(i).getExpense().toString());

        TextView userPhoneView = view.findViewById(R.id.valueItem);
        userPhoneView.setText("valor do pagamento: R$ "+payments.get(i).getValue());

        return view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}
