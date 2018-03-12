package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.azoubel.expensecontrol.data.model.ExpenseCategory;

public interface ExpenseCategoryDAO {

    @Insert
    void insertAll(ExpenseCategory... purchaseCategories);

    @Delete
    void delete(ExpenseCategory expenseCategory);
}
