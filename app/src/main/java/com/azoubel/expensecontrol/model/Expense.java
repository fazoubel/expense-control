package com.azoubel.expensecontrol.model;

import com.azoubel.expensecontrol.model.User.User;

import java.util.Date;

public class Expense{

    private int expenseId;

    private float initialValue;

    private Date expirationDate;

    private String description;

    private Date lastPaymentDate;

    private float assessment;

    private float finalValue;

    private Date expenseDate;

    private Store store;

    private User user;

    private ExpenseCategory category;

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
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

    @Override
    public String toString() {
        return String.format(this.description + "\n"
                + "loja: " + this.store.getStoreName() + "\n"
                + "categoria: " + this.getCategory().name() + "\n"
                + "valor da compra: R$ " + this.initialValue + "\n"
                + "data de vencimento: " + this.expirationDate + "\n");
    }
}
