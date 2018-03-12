package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.azoubel.expensecontrol.data.model.ExpenseCategoryData;

public interface ExpenseCategoryDAO {

    @Insert
    void insertAll(ExpenseCategoryData... purchaseCategories);

    @Delete
    void delete(ExpenseCategoryData expenseCategoryData);
}
