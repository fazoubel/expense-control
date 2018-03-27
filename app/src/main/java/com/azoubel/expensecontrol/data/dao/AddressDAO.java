package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.AddressData;


@Dao
public interface AddressDAO {

    @Insert
    void insertAll(AddressData... addressData);

    @Insert
    void insertAddress(AddressData addressData);

    @Update
    void update(AddressData addressData);

    @Query("SELECT * FROM AddressData WHERE (street = :street AND number = :number AND neighborhood = :neighborhood)" )
    AddressData getAddress(String street, int number, String neighborhood);

    @Delete
    void delete(AddressData addressData);
}
