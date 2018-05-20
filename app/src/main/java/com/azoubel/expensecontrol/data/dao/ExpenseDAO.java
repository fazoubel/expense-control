package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.ExpenseData;

import java.util.List;

@Dao
public interface ExpenseDAO {

    @Insert
    void insertAll(ExpenseData... expens);

    @Update
    void update(ExpenseData expenseData);

    @Query("SELECT * FROM ExpenseData WHERE expenseId = :expenseId")
    ExpenseData getExpense(int expenseId);

    @Query("SELECT * FROM ExpenseData WHERE user_id = :userId")
    List<ExpenseData> findByUser(int userId);

    @Query("SELECT * FROM ExpenseData WHERE user_id = :userId AND expiration_date >= :startDate AND expiration_date <= :endDate")
    List<ExpenseData> findByUser(long userId, long startDate, long endDate);

    @Query("SELECT * FROM ExpenseData WHERE user_id = :userId")
    List<ExpenseData> findByUser(long userId);

    @Delete
    void delete(ExpenseData ExpenseData);
}
