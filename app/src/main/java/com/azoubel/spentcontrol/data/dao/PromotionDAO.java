package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.spentcontrol.model.Promotion;

@Dao
public interface PromotionDAO {

    @Insert
    void insertAll(Promotion... promotions);

    @Update
    void update(Promotion promotion);

    @Query("SELECT * FROM promotion WHERE promotionId = :promotionId")
    Promotion getPromotion(int promotionId);

    @Delete
    void delete(Promotion promotion);
}
