package com.azoubel.expensecontrol.model;

public class User {

    public static final int SEX_MALE = 0;
    public static final int SEX_FEMALE = 1;

    private int userId;

    private String name;

    private String phoneNumber;

    private byte sex;

    private int image;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.name + "\n"
                + "telefone: " + this.phoneNumber+ "\n"
                + "sexo: " + this.getSexString();
    }

    private String getSexString() {
        return this.sex == SEX_MALE ? "masculino" : "feminino";
    }
}
