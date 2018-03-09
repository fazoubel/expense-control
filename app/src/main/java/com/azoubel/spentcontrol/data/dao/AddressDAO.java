package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.spentcontrol.model.Address;


@Dao
public interface AddressDAO {

    @Insert
    void insertAll(Address... addresses);

    @Update
    void update(Address address);

    @Query("SELECT * FROM address WHERE address_id  IN (:addressIds)")
    Address getAddress(int[] addressIds);

    @Delete
    void delete(Address address);
}
