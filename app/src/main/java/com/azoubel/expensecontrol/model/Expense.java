package com.azoubel.expensecontrol.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int expenseId;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "initial_value")
    private float initialValue;

    @ColumnInfo(name = "expiration_date")
    private Long expirationDate;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "store_id")
    private int storeId;

    @ColumnInfo(name = "last_payment_date")
    private Long lastPaymentDate;

    @ColumnInfo(name = "assessment")
    private float assessment;

    @ColumnInfo(name = "final_value")
    private float finalValue;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
}
