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
import com.azoubel.expensecontrol.model.Promotion;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.ui.view.DateView;

import java.util.Date;

public class PromotionActivity extends AbstractActivity{

    private final static int STORE_PICKER_REQUEST = 1;

    private EditText descriptionET;
    private EditText discountValueET;
    private DateView startDateDV;
    private EditText promotionNameET;
    private DateView endDateDV;
    private EditText paymentRestrictionET;
    private EditText prizeET;
    private DateView lotteryDate;
    private EditText phoneNumberET;
    private Button pickStoreBT;
    private EditText storeET;
    private Button saveBT;
    private Promotion promotion;
    private Store store;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null) {
            if (intent.hasExtra("id")) {
                promotion = controller.getPromotion(this, intent.getIntExtra("id", -1));
                store = promotion.getStore();
            }
        }
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_promotion;
    }

    @Override
    protected void init() {
        descriptionET = findViewById(R.id.promotionDescription);
        discountValueET = findViewById(R.id.discount);
        startDateDV = findViewById(R.id.startDate);
        promotionNameET = findViewById(R.id.promotionName);
        endDateDV = findViewById(R.id.endDate);
        paymentRestrictionET = findViewById(R.id.paymantWayResitrictionLabel);
        prizeET = findViewById(R.id.prize);
        lotteryDate = findViewById(R.id.lotteryDate);
        phoneNumberET = findViewById(R.id.phoneNumber);
        pickStoreBT = findViewById(R.id.storeButton);
        storeET = findViewById(R.id.store);
        saveBT = findViewById(R.id.save);
        this.fillComponents();
    }

    @Override
    protected void fillComponents() {
        if(this.promotion != null) {
            descriptionET.setText(promotion.getDescription());
            discountValueET.setText("" +promotion.getDiscountInPercent());
            startDateDV.fillComponents(promotion.getStartDate(), "Data de início");
            promotionNameET.setText(promotion.getName());
            endDateDV.fillComponents(promotion.getEndDate(), "válida até:");
            paymentRestrictionET.setText(promotion.getPaymentWayRestriction());
            prizeET.setText(promotion.getPrize());
            lotteryDate.fillComponents(promotion.getPrizeLotteryDate(), "data do sorteio:");
            phoneNumberET.setText(promotion.getPhoneNumber());
            if(this.store != null) {
                storeET.setText(store.getStoreName());
            }
        }

        startDateDV.fillComponents(null, "Data de início");
        endDateDV.fillComponents(null, "válida até");
        lotteryDate.fillComponents(null, "data do sorteio");

        pickStoreBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storePickerActivity = new Intent(PromotionActivity.this, StorePickerActivity.class);
                startActivityForResult(storePickerActivity, STORE_PICKER_REQUEST);
            }
        });

        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            int id = Integer.parseInt(data.getStringExtra("id"));
            if(requestCode == STORE_PICKER_REQUEST) {
                store = controller.getStore(PromotionActivity.this, id);
                storeET.setText(store.getStoreName());
            }
        }
    }

    @Override
    protected void save() {
        if(promotion == null) {
            buildPromotion();
            controller.addPromotion(this, promotion);
        }
        else{
            buildPromotion();
            controller.updatePromotion(this, promotion);
        }
        finish();
    }

    private void buildPromotion() {

        if(promotion == null) {
            promotion = new Promotion();
        }

        promotion.setDescription(descriptionET.getText().toString());

        Date startDate = startDateDV.buildDate();
        promotion.setStartDate(startDate);

        if(!TextUtils.isEmpty(discountValueET.getText().toString())) {
            promotion.setDiscountInPercent(Float.parseFloat(discountValueET.getText().toString()));
        }

        promotion.setName(promotionNameET.getText().toString());

        Date endDate = endDateDV.buildDate();
        promotion.setEndDate(endDate);

        promotion.setPaymentWayRestriction(paymentRestrictionET.getText().toString());

        promotion.setPrize(prizeET.getText().toString());

        Date lotteryPrizeDate = lotteryDate.buildDate();
        promotion.setPrizeLotteryDate(lotteryPrizeDate);

        promotion.setPhoneNumber(phoneNumberET.getText().toString());

        promotion.setStore(store);

    }
}
