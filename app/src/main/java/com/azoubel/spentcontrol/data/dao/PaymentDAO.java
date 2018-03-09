package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.spentcontrol.model.Payment;

@Dao
public interface PaymentDAO {

    @Insert
    void insertAll(Payment... payments);

    @Update
    void update(Payment payment);

    @Query("SELECT * FROM payment WHERE paymentId = :paymentId")
    Payment getPayment(int paymentId);

    @Delete
    void delete(Payment payment);
}
