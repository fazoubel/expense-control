package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.PaymentData;

import java.util.List;

@Dao
public interface PaymentDAO {

    @Insert
    void insertAll(PaymentData... paymentData);

    @Insert
    void insertPayment(PaymentData paymentData);

    @Update
    void update(PaymentData paymentData);

    @Query("SELECT * FROM PaymentData WHERE paymentId = :paymentId")
    PaymentData getPayment(int paymentId);

    @Query("SELECT * FROM PaymentData WHERE expense_id = :expenseId")
    List<PaymentData> findPaymentsByExpense(long expenseId);

    @Delete
    void delete(PaymentData paymentData);
}
