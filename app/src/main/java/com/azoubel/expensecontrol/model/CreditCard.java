package com.azoubel.expensecontrol.model;

import com.azoubel.expensecontrol.model.User.User;

public class CreditCard {

    private String number;

    private User user;

    private String expiration_date;

    private String flag;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return number + "\n"
                + flag + "\n";
    }
}
