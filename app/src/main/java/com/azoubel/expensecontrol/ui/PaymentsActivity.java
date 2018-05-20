package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.ui.view.DateView;

import java.util.Date;

public class PaymentsActivity extends AbstractActivity{

    private EditText expenseNameET;
    private Button payerBT;
    private EditText payerET;
    private EditText paymentWayET;
    private DateView paymentDateDV;
    private EditText paymentValueET;
    private Button creditCardBT;
    private EditText creditCardET;
    private Expense expense;
    private Payment payment;
    private Person payer;
    private Button saveBT;

    private final static int BUYER_PICKER_REQUEST = 0;
    private final static int CREDIT_CARD_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                payment = controller.getPayment(this, intent.getIntExtra("id", -1));
                payer = payment.getPayer();
                expense = payment.getExpense();
            }
            else if(intent.hasExtra("expense_id")) {
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
        paymentDateDV = findViewById(R.id.paymentDate);
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

        saveBT = findViewById(R.id.save);
        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        fillComponents();
    }

    @Override
    protected void fillComponents() {
        if(payment != null) {
            expenseNameET.setText(payment.getExpense().getDescription());
            payer = payment.getPayer();
            payerET.setText(payer.getFirstName() + " " + payment.getPayer().getLastName());
            paymentWayET.setText(payment.getPaymentWay().toString());
            paymentDateDV.fillComponents(payment.getPaymentDate(), "Data do pagamento");
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
            buildPayment();
            controller.addPayment(this, payment);
        }
        else {
            buildPayment();
            controller.updatePayment(this, payment);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == BUYER_PICKER_REQUEST) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                payer = controller.getPerson(PaymentsActivity.this, id);
                payerET.setText(payer.getFirstName());
                creditCardET.setText("");
            }
            else if (requestCode == CREDIT_CARD_PICKER_REQUEST) {
                String creditCardNumber = data.getStringExtra("credit_card_number");
                creditCardET.setText(creditCardNumber);
            }
        }
    }

    private void buildPayment() {
        if(payer != null && expense != null) {
            if(payment == null) {
                payment = new Payment();
            }
            payment.setExpense(expense);
            payment.setPayer(payer);
            payment.setPaymentDate(paymentDateDV.buildDate());
            PaymentWay paymentWay = PaymentWay.valueOf(paymentWayET.getText().toString());
            if(paymentWay != null) {
                payment.setPaymentWay(paymentWay);
            }
            payment.setValue(Float.parseFloat(paymentValueET.getText().toString()));
            CreditCard creditCard = controller.getCreditCard(this, creditCardET.getText().toString());
            if(creditCard != null) {
                payment.setCreditCard(creditCard);
            }
        }
    }
}
