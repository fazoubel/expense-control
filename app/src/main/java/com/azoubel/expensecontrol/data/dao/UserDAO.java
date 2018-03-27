package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.UserData.UserData;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertAll(UserData... userData);

    @Update
    void update(UserData userData);

    @Query("SELECT * FROM UserData")
    List<UserData> getAll();

    @Query("SELECT * FROM UserData WHERE userId = :userId")
    UserData getUser(int userId);

    @Query("SELECT * FROM UserData WHERE userId IN (:userIds)")
    List<UserData> getAllUsersById(int[] userIds);

    /*@Query("SELECT * FROM UserData WHERE name LIKE :name LIMIT 1")
    UserData findByName(String name);*/

    @Delete
    void delete(UserData userData);
}
