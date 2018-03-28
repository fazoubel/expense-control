package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class CarData extends UserData {

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
    private int ownerId;

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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
