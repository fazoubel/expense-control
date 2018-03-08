package com.azoubel.spentcontrol.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SpentCategory {

    @PrimaryKey
    private int spentCategoryId;

    @ColumnInfo(name = "name")
    private String name;

    public int getSpentCategoryId() {
        return spentCategoryId;
    }

    public void setSpentCategoryId(int spentCategoryId) {
        this.spentCategoryId = spentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
