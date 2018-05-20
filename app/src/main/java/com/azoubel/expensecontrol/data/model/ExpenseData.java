package com.azoubel.expensecontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class ExpenseData {

    @PrimaryKey(autoGenerate = true)
    private int expenseId;

    @ColumnInfo(name = "initial_value")
    private float initialValue;

    @ColumnInfo(name = "expiration_date")
    private Date expirationDate;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "assessment")
    private float assessment;

    @ColumnInfo(name = "final_value")
    private float finalValue;

    @ColumnInfo(name = "user_id")
    private long userId;

    @ColumnInfo(name = "buying_date")
    private Date buyingDate;

    @ColumnInfo(name = "store_id")
    private int storeId;

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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


    public Date getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }
}
