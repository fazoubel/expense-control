package com.azoubel.spentcontrol.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Spent {

    @PrimaryKey
    private int spentId;

    @ColumnInfo(name = "initial_value")
    private float initialValue;

    @ColumnInfo(name = "expiration_date")
    private Date expirationDate;

    @ColumnInfo(name = "description")
    private String name;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "story_id")
    private int storyId;

    @ColumnInfo(name = "last_paymant_date")
    private Date lastPaymentDate;

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
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

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
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
