package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.azoubel.spentcontrol.model.SpentCategory;

public interface SpentCategoryDAO {

    @Insert
    void insertAll(SpentCategory... purchaseCategories);

    @Delete
    void delete(SpentCategory spentCategory);
}
