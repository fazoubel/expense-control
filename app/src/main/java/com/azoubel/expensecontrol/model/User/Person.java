package com.azoubel.expensecontrol.model.User;

import java.util.Date;

public class Person extends User {

    private Date birthday;

    private byte sex;

    private String firstName;

    private long lastName;

    private long nickName;

    private String phoneNumber;

    private int addressId;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
