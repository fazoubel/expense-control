package com.azoubel.spentcontrol.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Payment {

    @PrimaryKey
    private int spentId;

    @PrimaryKey
    private int userId;

    @ColumnInfo(name = "paymant_way_id")
    private int paymantWayId;

    @ColumnInfo(name = "value")
    private float value;

    @ColumnInfo(name = "creditCardNumber")
    private float creditCardNumber;

    public int getSpentId() {
        return spentId;
    }

    public void setSpentId(int spentId) {
        this.spentId = spentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymantWayId() {
        return paymantWayId;
    }

    public void setPaymantWayId(int paymantWayId) {
        this.paymantWayId = paymantWayId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(float creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
