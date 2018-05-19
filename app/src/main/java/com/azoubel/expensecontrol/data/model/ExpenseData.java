package com.azoubel.expensecontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class ExpenseData {

    @PrimaryKey(autoGenerate = true)
    private int expenseId;

    @NonNull
    @ColumnInfo(name = "initial_value")
    private float initialValue;

    @ColumnInfo(name = "expiration_date")
    private Long expirationDate;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "last_payment_date")
    private Long lastPaymentDate;

    @ColumnInfo(name = "assessment")
    private float assessment;

    @ColumnInfo(name = "final_value")
    private float finalValue;

    @NonNull
    @ColumnInfo(name = "user_id")
    private long userId;

    @NonNull
    @ColumnInfo(name = "buying_date")
    private long buyingDate;

    @ColumnInfo(name = "store_id")
    private int storeId;

    @NonNull
    @ColumnInfo(name = "category")
    private String category;

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

    public float getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Long getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Long lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public float getAssessment() {
        return assessment;
    }

    public void setAssessment(float assessment) {
        this.assessment = assessment;
    }

    public float getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(float finalValue) {
        this.finalValue = finalValue;
    }


    public Long getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(long buyingDate) {
        this.buyingDate = buyingDate;
    }
}
