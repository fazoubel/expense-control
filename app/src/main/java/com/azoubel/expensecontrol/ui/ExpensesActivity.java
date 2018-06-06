package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.ui.view.DateView;

import java.util.Date;

public class ExpensesActivity extends AbstractActivity{

    private EditText nameET;
    private EditText descriptionET;
    private DateView expirationDateDV;
    private EditText initialValueET;
    private EditText finalValueET;
    private EditText categoryET;
    private EditText buyerPickerBT;
    private EditText buyerET;
    private Button storePickerBT;
    private EditText storeET;
    private EditText assessmentET;
    private DateView expenseDateDV;
    private Button saveBT;
    private Expense expense;
    private Person buyer;
    private Store store;

    private final static int STORE_PICKER_REQUEST = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if (intent.hasExtra("id")) {
                expense = controller.getExpense(this, intent.getIntExtra("id", -1));
                buyer = expense.getBuyer();
                store = expense.getStore();
            }
            else {
                buyer = controller.getPerson(this, intent.getLongExtra("buyer", -1));
            }
        }
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_expenses;
    }

    @Override
    protected void init() {
        nameET = findViewById(R.id.expenseName);
        descriptionET = findViewById(R.id.description);
        expirationDateDV = findViewById(R.id.expirationDate);
        initialValueET = findViewById(R.id.initialValue);
        finalValueET = findViewById(R.id.finalValue);
        categoryET = findViewById(R.id.category);
        buyerPickerBT = findViewById(R.id.buyerButton);
        buyerET = findViewById(R.id.buyer);
        storePickerBT = findViewById(R.id.storeButton);
        storeET = findViewById(R.id.store);
        assessmentET = findViewById(R.id.assessment);
        expenseDateDV = findViewById(R.id.expenseDate);
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
        if(expense != null) {
            nameET.setText(expense.getDescription());
            descriptionET.setText(expense.getDescription());
            if(expense.getExpenseDate() != null) {
                expirationDateDV.fillComponents(expense.getExpirationDate(), "Data de expiração da compra");
            }
            initialValueET.setText(""+expense.getInitialValue());
            finalValueET.setText(""+expense.getFinalValue());
            categoryET.setText(expense.getCategory().toString());

            assessmentET.setText(""+expense.getAssessment());
            if(expense.getExpenseDate() != null) {
                expenseDateDV.fillComponents(expense.getExpenseDate(), "Data da compra:");
            }
            if(store != null) {
                storeET.setText(store.getStoreName());
            }


        }

        storePickerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storePickerActivity = new Intent(ExpensesActivity.this, StorePickerActivity.class);
                startActivityForResult(storePickerActivity, STORE_PICKER_REQUEST);
            }
        });

        if(buyer != null) {
            buyerET.setText(buyer.getFirstName() + " " + buyer.getLastName());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            int id = Integer.parseInt(data.getStringExtra("id"));
            if(requestCode == STORE_PICKER_REQUEST) {
                store = controller.getStore(ExpensesActivity.this, id);
                storeET.setText(store.getStoreName());
            }
        }
    }

    @Override
    protected void save() {
        if(expense == null) {
            buildExpense();
            controller.addExpense(this, expense);
        }
        else{
            buildExpense();
            controller.updateExpense(this, expense);
        }
        finish();
    }

    private void buildExpense() {

        if(buyer != null) {
            if(expense == null) {
                expense = new Expense();
            }
            expense.setBuyer(buyer);
            expense.setStore(store);
            if(!TextUtils.isEmpty(initialValueET.getText().toString())) {
                expense.setInitialValue(Float.parseFloat(initialValueET.getText().toString()));
            }
            expense.setExpirationDate(expirationDateDV.buildDate());
            expense.setDescription(descriptionET.getText().toString());
            expense.setCategory((categoryET.getText().toString()));

            if(!TextUtils.isEmpty(assessmentET.getText().toString())) {
                expense.setAssessment(Float.parseFloat(assessmentET.getText().toString()));
            }
            expense.setExpenseDate(expenseDateDV.buildDate());
        }

    }
}
