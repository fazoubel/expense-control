package com.azoubel.expensecontrol.controller;

import android.content.Context;

import com.azoubel.expensecontrol.data.model.AddressData;
import com.azoubel.expensecontrol.data.model.ExpenseData;
import com.azoubel.expensecontrol.data.model.ExpenseCategoryData;
import com.azoubel.expensecontrol.data.model.StoreData;
import com.azoubel.expensecontrol.data.model.UserData;
import com.azoubel.expensecontrol.data.AppDatabase;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeController {

    private static HomeController instance;

    private HomeController(){}

    public static HomeController getInstance() {
        if(instance == null) {
            instance = new HomeController();
        }
        return instance;
    }

    public void insertUser(final Context context, String name, String phoneNumber, byte sex, int image) {
        final UserData userData = new UserData(name, phoneNumber, sex, image);
        AppDatabase.getInstance(context).userDAO().insertAll(userData);
    }

    public List<User> loadUsers(final Context context) {
        List<UserData> userDataList = AppDatabase.getInstance(context).userDAO().getAll();
        return buildUsers(userDataList);
    }

    public void addExpense(Context context, int userId, int storeId, float initialValue, long expirationDate, String description,
                           ExpenseCategory expenseCategory, float assessment) {

        ExpenseData expenseData = new ExpenseData();
        expenseData.setUserId(userId);
        expenseData.setInitialValue(initialValue);
        expenseData.setExpirationDate(expirationDate);
        expenseData.setStoreId(storeId);
        expenseData.setAssessment(assessment);
        expenseData.setCategory(expenseCategory.name());
        expenseData.setDescription(description);
        AppDatabase.getInstance(context).expenseDAO().insertAll(expenseData);

    }

    public void addPayment(Context context, int userId, PaymentWay paymentWay, float value, String creditCardNumber) {

    }

    public List<Expense> findExpenseByUser(Context context, int userId, long startDate, long endDate) {
        List<ExpenseData> expenseDataList = AppDatabase.getInstance(context).expenseDAO().findByUser(userId, startDate, endDate);
        return buildExpense(context, expenseDataList);
    }

    private List<User> buildUsers(List<UserData> userDataList) {
        List<User> userList = new ArrayList<>();
        for (UserData userData : userDataList) {
            User user  = buildUser(userData);
            userList.add(user);
        }
        return userList;
    }

    private User buildUser(UserData userData) {
        User user = new User();
        user.setUserId(userData.getUserId());
        user.setName(userData.getName());
        user.setPhoneNumber(userData.getPhoneNumber());
        user.setSex(userData.getSex());
        user.setImage(userData.getImage());
        return user;
    }

    private List<Expense> buildExpense(Context context, List<ExpenseData> expenseDataList) {
        List<Expense> expenseList = new ArrayList<>();
        for (ExpenseData expenseData : expenseDataList) {
            Expense expense = new Expense();

            expense.setExpenseId(expenseData.getExpenseId());

            UserData userData = AppDatabase.getInstance(context).userDAO().getUser(expenseData.getUserId());
            User user = buildUser(userData);
            expense.setUser(user);

            expense.setDescription(expenseData.getDescription());

            StoreData storeData = AppDatabase.getInstance(context).storeDAO().getStore(expenseData.getStoreId());
            Store store = buildStore(context, storeData);
            expense.setStore(store);

            expense.setCategory(ExpenseCategory.valueOf(expenseData.getCategory()));

            Date expirationDate = new Date(expenseData.getExpirationDate());
            expense.setExpirationDate(expirationDate);

            expense.setInitialValue(expenseData.getInitialValue());

            expense.setFinalValue(expenseData.getFinalValue());

            Date lastPaymentDate = new Date(expenseData.getLastPaymentDate());
            expense.setLastPaymentDate(lastPaymentDate);

            expense.setAssessment(expenseData.getAssessment());

        }

        return expenseList;
    }

    private Store buildStore(Context context, StoreData storeData) {
        Store store = new Store();
        store.setStoreId(storeData.getStoreId());
        store.setStoreName(storeData.getStoreName());
        store.setDescription(storeData.getDescription());
        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(storeData.getAddressId());
        Address address = buildAddress(addressData);
        store.setAddress(address);
        store.setSite(storeData.getSite());
        return store;
    }

    private Address buildAddress(AddressData addressData) {
        Address address = new Address();
        address.setAddressId(addressData.getAddressId());
        address.setStreet(addressData.getStreet());
        address.setNumber(addressData.getNumber());
        address.setCity(addressData.getCity());
        address.setState(addressData.getState());
        address.setCountry(addressData.getCountry());
        address.setZipCode(addressData.getZipCode());
        return address;
    }
}
