package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserData {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name = "image")
    private int image;

    @ColumnInfo(name = "expected_expenses_value")
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
}
