package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.User.Person;

public class PaymentsActivity extends AbstractActivity{

    private EditText expenseNameET;
    private Button payerBT;
    private EditText payerET;
    private EditText paymentWayET;
    private EditText paymentDateET;
    private EditText paymentValueET;
    private Button creditCardBT;
    private EditText creditCardET;
    private Expense expense;
    private Payment payment;
    private Person payer;

    private final static int BUYER_PICKER_REQUEST = 0;
    private final static int CREDIT_CARD_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                payment = controller.getPayment(this, intent.getIntExtra("id", -1));
            }
            if(intent.hasExtra("expense_id")) {
                expense = controller.getExpense(this, intent.getIntExtra("expense_id", -1));
            }
        }

        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_payments;
    }

    @Override
    protected void init() {
        expenseNameET = findViewById(R.id.expenseName);
        payerBT = findViewById(R.id.payerButton);
        payerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userPickerActivity = new Intent(PaymentsActivity.this, UserPickerActivity.class);
                startActivityForResult(userPickerActivity, BUYER_PICKER_REQUEST);
            }
        });
        payerET = findViewById(R.id.payer);
        paymentWayET = findViewById(R.id.paymentWay);
        paymentDateET = findViewById(R.id.paymentDate);
        paymentValueET = findViewById(R.id.paymentValue);
        creditCardBT = findViewById(R.id.creditCardButton);
        creditCardBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(payer != null) {
                    Intent creditCardPickerActivity = new Intent(PaymentsActivity.this, CreditCardPickerActivity.class);
                    creditCardPickerActivity.putExtra("id", payer.getUserId());
                    startActivityForResult(creditCardPickerActivity, CREDIT_CARD_PICKER_REQUEST);
                }
            }
        });
        creditCardET = findViewById(R.id.creditCard);
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        if(payment != null) {
            expenseNameET.setText(payment.getExpense().getDescription());
            payer = payment.getPayer();
            payerET.setText(payer.getFirstName() + " " + payment.getPayer().getLastName());
            paymentWayET.setText(payment.getPaymentWay().toString());
            paymentDateET.setText(payment.getPaymentDate().toString());
            paymentValueET.setText(""+payment.getValue());
            if(payment.getCreditCard() != null) {
                creditCardET.setText(payment.getCreditCard().getNumber());
            }
        }
        else {
            expenseNameET.setText(expense.getDescription());
        }
    }

    @Override
    protected void save() {
        if(payment == null) {
            controller.addPayment(PaymentsActivity.this, payer, expense, PaymentWay.valueOf(paymentWayET.getText().toString()),
                    Float.parseFloat(paymentValueET.getText().toString()), creditCardET.getText().toString());
        }
        else {
            //update payment
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == BUYER_PICKER_REQUEST) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                payer = controller.getPerson(PaymentsActivity.this, id);
                payerET.setText(payer.getFirstName());
            }
            else if (requestCode == CREDIT_CARD_PICKER_REQUEST) {
                String creditCardNumber = data.getStringExtra("credit_card_number");
                creditCardET.setText(creditCardNumber);
            }
        }
    }
}
