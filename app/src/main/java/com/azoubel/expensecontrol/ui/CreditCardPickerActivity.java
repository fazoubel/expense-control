package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.ui.view.CreditCardAdapter;

import java.util.List;


public class CreditCardPickerActivity extends AbstractActivity{

    private ListView creditCardListView;
    private Button pickerButton;
    private Person owner;
    private CreditCardAdapter creditCardAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if (intent.hasExtra("id")) {
                owner = controller.getPerson(this, intent.getLongExtra("id", -1));
            }
        }
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_credit_card_picker;
    }

    @Override
    protected void init() {
        creditCardListView = findViewById(R.id.list_credit_cards);
        pickerButton = findViewById(R.id.pickerButton);
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        List<CreditCard> creditCards = controller.getCreditCardsOfUser(this, owner);
        creditCardAdapter = new CreditCardAdapter(creditCards, this);
        creditCardListView.setAdapter(creditCardAdapter);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    @Override
    protected void save() {
        CreditCard selectedCreditCard = creditCardAdapter.getSelectedCreditCard();
        if(selectedCreditCard != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("credit_card_number", selectedCreditCard.getNumber());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
