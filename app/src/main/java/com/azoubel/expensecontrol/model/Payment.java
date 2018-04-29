package com.azoubel.expensecontrol.model;

import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.User;

import java.util.Date;

public class Payment {

    private int paymentId;

    private Expense expense;

    private Person payer;

    private PaymentWay paymentWay;

    private float value;

    private Date paymentDate; 
    
    private CreditCard creditCard;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Person getPayer() {
        return payer;
    }

    public void setPayer(Person payer) {
        this.payer = payer;
    }

    public PaymentWay getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return String.format(expense.getDescription() + "\n"
                + payer.getFirstName() + payer.getLastName() + "\n"
                +"R$ "+value + "\n"
                + paymentDate + "\n"
                + paymentWay + "\n"
                + creditCard + "\n");
    }
}
