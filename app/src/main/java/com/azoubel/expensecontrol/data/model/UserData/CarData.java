package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/*@Entity(
        //indices = {@Index(value = "user_id", unique = true)},
        foreignKeys = {
        @ForeignKey(
                entity = UserData.class,
                parentColumns = "id",
                childColumns = "user_id"
        )
}
)*/
@Entity()
public class CarData {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private long userId;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "plate_number")
    private String plateNumber;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "owner")
    private long ownerId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
