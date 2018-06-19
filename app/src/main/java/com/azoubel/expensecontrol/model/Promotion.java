package com.azoubel.expensecontrol.model;

import java.util.Date;

public class Promotion {

    private int promotionId;

    private String name;

    private String description;

    private float discountInPercent;

    private Date startDate;

    private Date endDate;

    private Store store;

    private String paymentWayRestriction;

    private String prize;

    private Date prizeLotteryDate;

    private String phoneNumber;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    public Date getPrizeLotteryDate() {
        return prizeLotteryDate;
    }

    public void setPrizeLotteryDate(Date prizeLotteryDate) {
        this.prizeLotteryDate = prizeLotteryDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return description;
    }
}
