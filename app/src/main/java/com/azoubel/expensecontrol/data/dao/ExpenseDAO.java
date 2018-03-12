package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.Expense;

import java.util.List;

@Dao
public interface ExpenseDAO {

    @Insert
    void insertAll(Expense... expenses);

    @Update
    void update(Expense expense);

    @Query("SELECT * FROM Expense WHERE expenseId = :expenseId")
    Expense getExpense(int expenseId);

    @Query("SELECT * FROM Expense WHERE user_id = :userId")
    List<Expense> findByUser(int userId);

    @Query("SELECT * FROM Expense WHERE user_id = :userId AND expiration_date >= :startDate AND expiration_date <= :endDate")
    List<Expense> findByUser(int userId, long startDate, long endDate);

    @Delete
    void delete(Expense Expense);
}
