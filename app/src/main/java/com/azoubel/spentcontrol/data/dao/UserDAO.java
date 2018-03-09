package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.spentcontrol.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertAll(User... users);

    @Update
    void update(User user);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId = :userId")
    List<User> getUser(int userId);

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    List<User> getAllUsersById(int[] userIds);

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    User findByName(String name);

    @Delete
    void delete(User user);
}
