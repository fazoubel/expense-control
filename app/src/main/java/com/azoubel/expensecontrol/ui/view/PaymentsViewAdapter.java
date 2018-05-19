package com.azoubel.expensecontrol.ui.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.User.User;

import java.util.List;

public class PaymentsViewAdapter extends BaseAdapter {

    private List<Payment> payments;
    private Activity activity;
    private View selectedView;
    private Payment selectedPayment;
    private PaymentsView.PaymentClickListener listener;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.list_item_image);
        if(payments.get(i).getPayer() != null) {
            imageView.setImageDrawable(activity.getDrawable(getUserImage(payments.get(i).getPayer().getImage())));
        }

        TextView paymentDescription = view.findViewById(R.id.listItemDescription);
        paymentDescription.setText(payments.get(i).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(Color.BLUE);
                if(selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
                selectedView = view;
                selectedPayment = getItem(i);
                if(listener != null) {
                    listener.onPaymentClicked(getItem(i).getPaymentId());
                }

            }
        });

        return view;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void setPaymentClickListener(PaymentsView.PaymentClickListener listener) {
        this.listener = listener;
    }

    public Payment getSelectedPayment() {
        return selectedPayment;
    }

    public void clearSelected() {
        selectedPayment = null;
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
