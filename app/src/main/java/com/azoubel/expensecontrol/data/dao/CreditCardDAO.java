package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.CreditCardData;

import java.util.List;

@Dao
public interface CreditCardDAO {

    @Insert
    void insertAll(CreditCardData... creditCardData);

    @Update
    void update(CreditCardData creditCardData);

    @Query("SELECT * FROM CreditCardData WHERE number = :creditCardNumber")
    CreditCardData getCreditCard(String creditCardNumber);

    @Query("SELECT * FROM CreditCardData WHERE user_id = :userId")
    List<CreditCardData> getCreditCardByUser(long userId);

    @Delete
    void delete(CreditCardData creditCardData);
}
