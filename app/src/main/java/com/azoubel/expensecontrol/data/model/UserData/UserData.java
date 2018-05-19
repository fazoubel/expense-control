package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserData {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "image")
    private int image;

    @ColumnInfo(name = "expected_expenses_value")
    private float expectedExpensesValue;

    @ColumnInfo(name = "address_id")
    private int addressId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
