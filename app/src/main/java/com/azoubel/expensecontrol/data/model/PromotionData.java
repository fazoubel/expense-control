package com.azoubel.expensecontrol.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class PromotionData {

    @PrimaryKey(autoGenerate = true)
    private int promotionId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "discount_in_percent")
    private float discountInPercent;

    @ColumnInfo(name = "start_date")
    private Long startDate;

    @ColumnInfo(name = "end_date")
    private Long endDate;

    @ColumnInfo(name = "store_id")
    private int storeId;

    @ColumnInfo(name = "payment_way_restriction")
    private String paymentWayRestriction;

    @ColumnInfo(name = "prize")
    private String prize;

    @ColumnInfo(name = "prize_lottery_date")
    private Long prizeLotteryDate;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "brand")
    private String brand;

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscountInPercent() {
        return discountInPercent;
    }

    public void setDiscountInPercent(float discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getPaymentWayRestriction() {
        return paymentWayRestriction;
    }

    public void setPaymentWayRestriction(String paymentWayRestriction) {
        this.paymentWayRestriction = paymentWayRestriction;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Long getPrizeLotteryDate() {
        return prizeLotteryDate;
    }

    public void setPrizeLotteryDate(Long prizeLotteryDate) {
        this.prizeLotteryDate = prizeLotteryDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
