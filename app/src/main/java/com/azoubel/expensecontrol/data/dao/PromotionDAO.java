package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.PromotionData;

import java.util.List;

@Dao
public interface PromotionDAO {

    @Insert
    void insertAll(PromotionData... promotionData);

    @Update
    void update(PromotionData promotionData);

    @Query("SELECT * FROM PromotionData WHERE promotionId = :promotionId")
    PromotionData getPromotion(int promotionId);

    @Query("SELECT * FROM PromotionData WHERE store_id = :storeId")
    List<PromotionData> getAllPromotionByStore(int storeId);

    @Query("SELECT * FROM PromotionData")
    List<PromotionData> getAllPromotions();

    @Delete
    void delete(PromotionData promotionData);
}
