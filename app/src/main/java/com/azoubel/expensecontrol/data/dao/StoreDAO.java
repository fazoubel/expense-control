package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.StoreData;

import java.util.List;

@Dao
public interface StoreDAO {

    @Insert
    void insertAll(StoreData... storeData);

    @Update
    void update(StoreData storeData);

    @Query("SELECT * FROM StoreData")
    List<StoreData> getAll();

    @Query("SELECT * FROM StoreData WHERE storeId = :storeId")
    StoreData getStore(int storeId);

    @Query("SELECT * FROM StoreData WHERE store_name LIKE :storeName")
    StoreData findStoreByName(String storeName);

    @Delete
    void delete(StoreData storeData);
}
