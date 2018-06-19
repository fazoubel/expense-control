package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.azoubel.expensecontrol.data.model.DiscountData;

import java.util.List;

@Dao
public interface DiscountDAO {

    @Insert
    void insertAll(DiscountData... discountData);

    @Query("SELECT * FROM DiscountData WHERE expenseId = :expenseId")
    List<DiscountData> getDiscounts(long expenseId);

    @Query("DELETE FROM DiscountData WHERE expenseId = :expenseId")
    void deleteDiscounts(long expenseId);

    @Delete
    void delete(DiscountData discountData);

}
