package com.azoubel.spentcontrol.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Discount {

    @PrimaryKey
    private int spentId;

    @PrimaryKey
    private int promotionId;

    public int getSpentId() {
        return spentId;
    }

    public void setSpentId(int spentId) {
        this.spentId = spentId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }
}
