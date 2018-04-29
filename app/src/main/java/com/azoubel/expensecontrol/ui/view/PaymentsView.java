package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ListView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.Payment;

import java.util.List;

public class PaymentsView extends ConstraintLayout {

    public interface PaymentClickListener {
        void onPaymentClicked(int paymentId);
    }

    private ListView listView;
    private PaymentsViewAdapter adapter;
    private Expense expense;

    public PaymentsView(Context context) {
        super(context);
        init();
    }

    public PaymentsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.view_payments, this);
        listView = findViewById(R.id.paymentsListView);
    }

    public void setData(List<Payment> payments, Activity activity) {
        if(adapter == null) {
            adapter = new PaymentsViewAdapter(payments, activity);
            listView.setAdapter(adapter);
        }
        else {
            adapter.setPayments(payments);
            adapter.setActivity(activity);
            adapter.notifyDataSetChanged();
        }
    }

    public void setPaymentClickListener(PaymentsView.PaymentClickListener listener) {
        if(adapter != null) {
            adapter.setPaymentClickListener(listener);
        }
    }

    public Payment getSelectedPayment() {
        return adapter.getSelectedPayment();
    }

    public void clearSelected() {
        if(adapter != null) {
            adapter.clearSelected();
        }
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
