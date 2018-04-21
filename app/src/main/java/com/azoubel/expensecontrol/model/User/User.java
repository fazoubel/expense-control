package com.azoubel.expensecontrol.model.User;

import android.arch.persistence.room.Entity;

@Entity
public class User{

    public static final int SEX_MALE = 0;
    public static final int SEX_FEMALE = 1;

    private int userId;

    private int image;

    private float expectedExpensesValue;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getExpectedExpensesValue() {
        return expectedExpensesValue;
    }

    public void setExpectedExpensesValue(float expectedExpensesValue) {
        this.expectedExpensesValue = expectedExpensesValue;
    }

    /*private String getSexString() {
        return this.sex == SEX_MALE ? "masculino" : "feminino";
    }*/
}
