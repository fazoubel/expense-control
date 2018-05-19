package com.azoubel.expensecontrol.model.User;

import android.arch.persistence.room.Entity;

import com.azoubel.expensecontrol.model.Address;

@Entity
public class User {

    public static final int IMAGE_BABE = 0;
    public static final int IMAGE_BOY = 1;
    public static final int IMAGE_CAT = 2;
    public static final int IMAGE_DOG = 3;
    public static final int IMAGE_FATHER = 4;
    public static final int IMAGE_GIRL = 5;
    public static final int IMAGE_OLD_MAN = 6;
    public static final int IMAGE_OLD_WOMAN = 7;
    public static final int IMAGE_WOMAN = 8;


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
