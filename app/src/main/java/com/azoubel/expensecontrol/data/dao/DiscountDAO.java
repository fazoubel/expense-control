package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.azoubel.expensecontrol.data.model.DiscountData;

@Dao
public interface DiscountDAO {

    @Insert
    void insertAll(DiscountData... discountData);

    @Delete
    void delete(DiscountData discountData);
}
