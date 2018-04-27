package com.azoubel.expensecontrol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Expense;

public class ExpensesActivity extends AbstractActivity{

    private EditText nameET;
    private EditText descriptionET;
    private EditText productTypeET;
    private EditText phoneNumberET;
    private EditText emailET;
    private EditText managerNameET;
    private EditText managerPhoneNumberET;
    private EditText managerEmailET;
    private Expense expense;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("id")) {
            expense = controller.getExpense(this, intent.getIntExtra("id", -1));
        }
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_expenses;
    }

    @Override
    protected void init() {
        /*nameET = findViewById(R.id.expenseName);
        siteET = findViewById(R.id.paymentDate);
        descriptionET = findViewById(R.id.paymentWay);
        productTypeET = findViewById(R.id.paymentValue);
        phoneNumberET = findViewById(R.id.creditCard);
        emailET = findViewById(R.id.category);
        managerNameET = findViewById(R.id.buyer);
        managerPhoneNumberET = findViewById(R.id.store);
        managerEmailET = findViewById(R.id.assessment);
        addressView = findViewById(R.id.storeAddressView);
        addressView.setAddressSearcher(this);*/
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        if(expense != null) {
            /*nameET.setText(expense.getStoreName());
            siteET.setText(expense.getSite());
            descriptionET.setText(expense.getDescription());
            productTypeET.setText(expense.getProductType());
            phoneNumberET.setText(expense.getPhoneNumber());
            emailET.setText(expense.getEmail());
            managerNameET.setText(expense.getManagerName());
            managerPhoneNumberET.setText(expense.getManagerPhoneNumber());
            managerEmailET.setText(expense.getManagerEmail());
            addressView.fillAddress(expense.getAddress());*/
        }
    }

    @Override
    protected void save() {
        /*controller.addStore(this, nameET.getText().toString(), siteET.getText().toString(),
                descriptionET.getText().toString(), productTypeET.getText().toString(),
                phoneNumberET.getText().toString(), emailET.getText().toString(),
                managerNameET.getText().toString(), managerPhoneNumberET.getText().toString(),
                managerEmailET.getText().toString(), addressView.getAddress());*/
        finish();
    }
}
