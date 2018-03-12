package com.azoubel.expensecontrol.controller;

import android.content.Context;
import android.os.AsyncTask;

import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.User;
import com.azoubel.expensecontrol.data.AppDatabase;

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

        final User user = new User(name, phoneNumber, sex, image);

        AppDatabase.getInstance(context).userDAO().insertAll(user);

    }

    public List<User> loadUsers(final Context context) {
        return AppDatabase.getInstance(context).userDAO().getAll();
    }

    public void addExpense(Context context, int userId, int storeId, float initialValue, long expirationDate, String description,
                           ExpenseCategory.ExpenseCategoryEnum expenseCategory, String storeName, float assessment) {

        Expense expense = new Expense();
        expense.setUserId(userId);
        expense.setInitialValue(initialValue);
        expense.setExpirationDate(expirationDate);
        expense.setStoreId(storeId);
        expense.setAssessment(assessment);
        AppDatabase.getInstance(context).expenseDAO().insertAll(expense);

    }

    public void addPayment(Context context, int userId, PaymentWay.PaymentWayEnum paymentWay, float value, String creditCardNumber) {

    }

    public List<Expense> findExpenseByUser(Context context, int userId, long startDate, long endDate) {
        return AppDatabase.getInstance(context).expenseDAO().findByUser(userId, startDate, endDate);
    }


}
