package com.azoubel.expensecontrol.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.azoubel.expensecontrol.R;
import com.azoubel.expensecontrol.model.Promotion;
import com.azoubel.expensecontrol.ui.view.PromotionsView;

import java.util.List;

public class PromotionPickerActivity extends AbstractActivity{

    private PromotionsView promotionsView;
    private Button pickerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getContentViewXML() {
        return R.layout.activity_promotion_picker;
    }

    @Override
    protected void init() {
        promotionsView = findViewById(R.id.promotions_view_layout);
        pickerButton = findViewById(R.id.pickerButton);
        fillComponents();
    }

    @Override
    protected void fillComponents() {
        List<Promotion> stores = controller.getAllPromotions(this);
        promotionsView.setData(stores, this);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    @Override
    protected void save() {
        Promotion selectedPromotion = promotionsView.getSelectedPromotion();
        if(selectedPromotion != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", ""+selectedPromotion.getPromotionId());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}
