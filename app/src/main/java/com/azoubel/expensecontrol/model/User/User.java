package com.azoubel.expensecontrol.model.User;

import android.arch.persistence.room.Entity;

import com.azoubel.expensecontrol.model.Address;

@Entity
public class User {

    public static final int SEX_MALE = 0;
    public static final int SEX_FEMALE = 1;

    private long userId;

    private int image;

    private float expectedExpensesValue;

    private Address address;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /*private String getSexString() {
        return this.sex == SEX_MALE ? "masculino" : "feminino";
    }*/
}
