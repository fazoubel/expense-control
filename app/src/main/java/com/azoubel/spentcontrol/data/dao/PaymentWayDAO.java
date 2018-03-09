package com.azoubel.spentcontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.azoubel.spentcontrol.model.PaymentWay;

@Dao
public interface PaymentWayDAO {

    @Insert
    void insertAll(PaymentWay... paymentWays);

    @Delete
    void delete(PaymentWay paymentWay);
}
