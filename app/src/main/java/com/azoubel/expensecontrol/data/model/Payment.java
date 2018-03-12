package com.azoubel.expensecontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Payment {

    @PrimaryKey(autoGenerate = true)
    private int paymentId;

    @ColumnInfo(name = "expense_id")
    private int expenseId;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "payment_way_id")
    private int paymentWayId;

    @ColumnInfo(name = "value")
    private float value;

    @ColumnInfo(name = "creditCardNumber")
    private float creditCardNumber;

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentWayId() {
        return paymentWayId;
    }

    public void setPaymentWayId(int paymentWayId) {
        this.paymentWayId = paymentWayId;
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
