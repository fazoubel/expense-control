package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.CreditCardData;

@Dao
public interface CreditCardDAO {

    @Insert
    void insertAll(CreditCardData... creditCardData);

    @Update
    void update(CreditCardData address);

    @Query("SELECT * FROM CreditCardData WHERE number LIKE :creditCardNumber")
    CreditCardData getCreditCard(String creditCardNumber);

    @Delete
    void delete(CreditCardData creditCardData);
}
