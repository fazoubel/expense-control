package com.azoubel.expensecontrol.model.User;

import com.azoubel.expensecontrol.model.Address;

import java.util.Date;

public class Person extends User {

    private Date birthday;

    private int sex;

    private String firstName;

    private long lastName;

    private long nickName;

    private String phoneNumber;

    private Address address;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getLastName() {
        return lastName;
    }

    public void setLastName(long lastName) {
        this.lastName = lastName;
    }

    public long getNickName() {
        return nickName;
    }

    public void setNickName(long nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
