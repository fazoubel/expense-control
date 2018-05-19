package com.azoubel.expensecontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class PaymentData {

    @PrimaryKey(autoGenerate = true)
    private int paymentId;

    @NonNull
    @ColumnInfo(name = "expense_id")
    private int expenseId;

    @NonNull
    @ColumnInfo(name = "user_id")
    private long userId;

    @ColumnInfo(name = "payment_way")
    private String paymentWay;

    @NonNull
    @ColumnInfo(name = "value")
    private float value;

    @ColumnInfo(name = "creditCardNumber")
    private String creditCardNumber;

    @NonNull
    @ColumnInfo(name = "payment_date")
    private Long paymentDate;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }
}
