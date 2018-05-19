package com.azoubel.expensecontrol.data.model.UserData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*@Entity(
        //indices = {@Index(value = "user_id", unique = true)},
        foreignKeys = {
        @ForeignKey(
                entity = UserData.class,
                parentColumns = "id",
                childColumns = "user_id"
        )
}
)*/
@Entity()
public class PetData {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private long userId;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "breed")
    private String breed;

    @ColumnInfo(name = "species")
    private String species;

    @ColumnInfo(name = "sex")
    private String sex;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "nick_name")
    private String nickName;

    @ColumnInfo(name = "owner")
    private long ownerId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String kind) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
