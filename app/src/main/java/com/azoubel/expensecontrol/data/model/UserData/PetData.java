package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class PetData extends UserData {

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "breed")
    private String breed;

    @ColumnInfo(name = "kind")
    private String kind;

    @ColumnInfo(name = "sex")
    private int sex;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "nick_name")
    private String nickName;

    @ColumnInfo(name = "owner")
    private int ownerId;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
