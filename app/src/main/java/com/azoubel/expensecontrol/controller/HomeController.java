package com.azoubel.expensecontrol.controller;

import android.content.Context;

import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.model.User;
import com.azoubel.expensecontrol.data.AppDatabase;

import java.util.List;

public class HomeController {

    private Expense selectedExpense;
    private User selectedUser;
    private List<User> allUsers;
    private List<Expense> allExpenses;

    public void setSelectedExpense(int index) {

    }

    public void createUser(Context context, String name, String phoneNumber, byte sex, int image) {
        User user = new User(name, phoneNumber, sex, image);
        AppDatabase.getInstance(context).userDAO().insertAll(user);
    }

    public List<User> getAllUsers(Context context) {
        return AppDatabase.getInstance(context).userDAO().getAll();
    }

    public void addExpense(Context context, float initialValue, long expirationDate, String description,
                           ExpenseCategory.ExpenseCategoryEnum expenseCategory, String storeName, float assessment) {
        Expense expense = new Expense();

        //User user = AppDatabase.getInstance(context).userDAO().findByName(userName);

        if(selectedUser != null) {
            expense.setUserId(selectedUser.getUserId());
            expense.setInitialValue(initialValue);
            expense.setExpirationDate(expirationDate);

            Store store = AppDatabase.getInstance(context).storeDAO().findStoreByName(storeName);
            if(store != null) {
                expense.setStoreId(store.getStoreId());
            }

            expense.setAssessment(assessment);

            AppDatabase.getInstance(context).expenseDAO().insertAll(expense);
        }

    }

    public void addPayment(Context context, PaymentWay.PaymentWayEnum paymentWay, float value, String credtCardNumber) {

    }

    public List<Expense> findExpenseByUser(Context context, long startDate, long endDate) {
        if(selectedUser != null) {
            return AppDatabase.getInstance(context).expenseDAO().findByUser(selectedUser.getUserId(), startDate, endDate);
        }
        return null;
    }

    public Store findStoreByName(Context context, String storeName) {
        // TODO - return store
        return null;
    }
}
