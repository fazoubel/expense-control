package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;

public class HouseData extends UserData {

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "rooms")
    private int rooms;

    @ColumnInfo(name = "garages")
    private int garages;

    @ColumnInfo(name = "square")
    private int square;

    @ColumnInfo(name = "is_rented")
    private byte isRented;

    @ColumnInfo(name = "tenant")
    private int tenantId;

    @ColumnInfo(name = "addressId")
    private int addressId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getGarages() {
        return garages;
    }

    public void setGarages(int garages) {
        this.garages = garages;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public byte getIsRented() {
        return isRented;
    }

    public void setIsRented(byte isRented) {
        this.isRented = isRented;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
