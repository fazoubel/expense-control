package com.azoubel.spentcontrol.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Spent {

    @PrimaryKey
    private int spentId;

    @ColumnInfo(name = "initial_value")
    private float initialValue;

    @ColumnInfo(name = "expiration_date")
    private Long expirationDate;

    @ColumnInfo(name = "description")
    private String name;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "story_id")
    private int storyId;

    @ColumnInfo(name = "last_paymant_date")
    private Long lastPaymentDate;

    @ColumnInfo(name = "assessment")
    private float assessment;

    @ColumnInfo(name = "final_value")
    private float finalValue;

    public int getSpentId() {
        return spentId;
    }

    public void setSpentId(int spentId) {
        this.spentId = spentId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
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
