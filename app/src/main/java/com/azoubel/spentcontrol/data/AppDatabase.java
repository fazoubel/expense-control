package com.azoubel.spentcontrol.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.azoubel.spentcontrol.data.dao.CreditCardDAO;
import com.azoubel.spentcontrol.data.dao.SpentDAO;
import com.azoubel.spentcontrol.data.dao.UserDAO;
import com.azoubel.spentcontrol.model.CreditCard;
import com.azoubel.spentcontrol.model.Spent;
import com.azoubel.spentcontrol.model.User;

@Database(entities = {User.class, Spent.class, CreditCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public AppDatabase(){}

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "Spent-control-db").build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();

    public abstract SpentDAO purchaseDAO();

    public abstract CreditCardDAO creditCardDAO();
}
