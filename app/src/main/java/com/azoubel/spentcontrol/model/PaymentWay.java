package com.azoubel.spentcontrol.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PaymentWay {

    @PrimaryKey
    private int PaymentWayId;

    @ColumnInfo(name = "name")
    private String name;

    public int getPaymentWayId() {
        return PaymentWayId;
    }

    public void setPaymentWayId(int paymentWayId) {
        PaymentWayId = paymentWayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
