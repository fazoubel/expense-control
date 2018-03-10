package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.model.CreditCard;

@Dao
public interface CreditCardDAO {

    @Insert
    void insertAll(CreditCard... creditCards);

    @Update
    void update(CreditCard address);

    @Query("SELECT * FROM creditcard WHERE number LIKE :creditCardNumber")
    CreditCard getCreditCard(String creditCardNumber);

    @Delete
    void delete(CreditCard creditCard);
}
