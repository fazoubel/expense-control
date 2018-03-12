package com.azoubel.expensecontrol.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.azoubel.expensecontrol.data.dao.CreditCardDAO;
import com.azoubel.expensecontrol.data.dao.ExpenseDAO;
import com.azoubel.expensecontrol.data.dao.UserDAO;
import com.azoubel.expensecontrol.data.model.CreditCard;
import com.azoubel.expensecontrol.data.model.Expense;
import com.azoubel.expensecontrol.data.model.Store;
import com.azoubel.expensecontrol.data.model.User;
import com.azoubel.expensecontrol.data.dao.StoreDAO;

@Database(entities = {User.class, Expense.class, Store.class, CreditCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public AppDatabase(){}

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "Expense-control-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();

    public abstract ExpenseDAO expenseDAO();

    public abstract CreditCardDAO creditCardDAO();

    public abstract StoreDAO storeDAO();
}
