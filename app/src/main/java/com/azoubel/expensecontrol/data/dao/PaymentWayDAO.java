package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

@Dao
public interface PaymentWayDAO {

    @Insert
    void insertAll(PaymentWayData... paymentWayData);

    @Delete
    void delete(PaymentWayData paymentWayData);
}
