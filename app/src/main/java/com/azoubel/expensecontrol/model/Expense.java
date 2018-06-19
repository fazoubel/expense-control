package com.azoubel.expensecontrol.model;

import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.User;

import java.util.Date;
import java.util.List;

public class Expense{

    private long expenseId;

    private float initialValue;

    private Date expirationDate;

    private String description;

    private Date lastPaymentDate;

    private float assessment;

    private float finalValue;

    private Date expenseDate;

    private Store store;

    private Person buyer;

    private String category;

    private List<Promotion> discountList;

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public float getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public float getAssessment() {
        return assessment;
    }

    public void setAssessment(float assessment) {
        this.assessment = assessment;
    }

    public float getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(float finalValue) {
        this.finalValue = finalValue;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public List<Promotion> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<Promotion> discountList) {
        this.discountList = discountList;
    }

    @Override
    public String toString() {
        return String.format(this.description + "\n"
                + "comprador: " + this.buyer.getFirstName() + " " + buyer.getLastName() + "\n"
                + "valor da compra: R$ " + this.initialValue + "\n"
                + "valor à pagar: R$ " + this.finalValue + "\n"
                + "data de vencimento: " + this.expirationDate + "\n");
    }
}
