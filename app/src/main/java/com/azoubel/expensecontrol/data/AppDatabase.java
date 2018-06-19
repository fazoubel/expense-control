package com.azoubel.expensecontrol.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.azoubel.expensecontrol.data.dao.AddressDAO;
import com.azoubel.expensecontrol.data.dao.CreditCardDAO;
import com.azoubel.expensecontrol.data.dao.DiscountDAO;
import com.azoubel.expensecontrol.data.dao.ExpenseDAO;
import com.azoubel.expensecontrol.data.dao.PaymentDAO;
import com.azoubel.expensecontrol.data.dao.PromotionDAO;
import com.azoubel.expensecontrol.data.dao.UserDAO;
import com.azoubel.expensecontrol.data.model.AddressData;
import com.azoubel.expensecontrol.data.model.CreditCardData;
import com.azoubel.expensecontrol.data.model.DiscountData;
import com.azoubel.expensecontrol.data.model.ExpenseData;
import com.azoubel.expensecontrol.data.model.PaymentData;
import com.azoubel.expensecontrol.data.model.PromotionData;
import com.azoubel.expensecontrol.data.model.StoreData;
import com.azoubel.expensecontrol.data.model.UserData.CarData;
import com.azoubel.expensecontrol.data.model.UserData.HouseData;
import com.azoubel.expensecontrol.data.model.UserData.PersonData;
import com.azoubel.expensecontrol.data.model.UserData.PetData;
import com.azoubel.expensecontrol.data.model.UserData.UserData;
import com.azoubel.expensecontrol.data.dao.StoreDAO;

@Database(entities = {UserData.class, PersonData.class, PetData.class, CarData.class, HouseData.class, ExpenseData.class, StoreData.class, AddressData.class, CreditCardData.class, PaymentData.class, PromotionData.class, DiscountData.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public AppDatabase(){}

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "ExpenseData-control-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();

    public abstract ExpenseDAO expenseDAO();

    public abstract CreditCardDAO creditCardDAO();

    public abstract StoreDAO storeDAO();

    public abstract AddressDAO addressDAO();

    public abstract PaymentDAO paymentDAO();

    public abstract PromotionDAO promotionDAO();

    public abstract DiscountDAO discountDAO();
}
