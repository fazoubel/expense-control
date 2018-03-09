package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.spentcontrol.model.Spent;

@Dao
public interface SpentDAO {

    @Insert
    void insertAll(Spent... Spents);

    @Update
    void update(Spent spent);

    @Query("SELECT * FROM spent WHERE spentId = :spentId")
    Spent getSpent(int spentId);

    @Delete
    void delete(Spent Spent);
}
