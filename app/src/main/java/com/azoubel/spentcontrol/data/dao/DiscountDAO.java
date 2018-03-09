package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.azoubel.spentcontrol.model.Discount;

@Dao
public interface DiscountDAO {

    @Insert
    void insertAll(Discount... discounts);

    @Delete
    void delete(Discount discount);
}
