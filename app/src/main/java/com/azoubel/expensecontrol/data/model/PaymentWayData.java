package com.azoubel.expensecontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PaymentWayData {

    @PrimaryKey(autoGenerate = true)
    private String PaymentWay;

    @ColumnInfo(name = "name")
    private String name;

    public String getPaymentWayId() {
        return PaymentWay;
    }

    public void setPaymentWayId(String paymentWayId) {
        PaymentWay = paymentWayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
