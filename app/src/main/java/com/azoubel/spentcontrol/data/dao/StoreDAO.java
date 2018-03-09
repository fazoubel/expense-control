package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.spentcontrol.model.Store;

import java.util.List;

@Dao
public interface StoreDAO {

    @Insert
    void insertAll(Store... purchases);

    @Update
    void update(Store store);

    @Query("SELECT * FROM store")
    List<Store> getAll();

    @Query("SELECT * FROM store WHERE store_name LIKE :storeName")
    Store getStore(String storeName);

    @Delete
    void delete(Store purchase);
}
