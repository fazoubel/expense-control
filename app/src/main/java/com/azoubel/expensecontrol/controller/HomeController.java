package com.azoubel.expensecontrol.controller;

import android.content.Context;

import com.azoubel.expensecontrol.data.model.AddressData;
import com.azoubel.expensecontrol.data.model.ExpenseData;
import com.azoubel.expensecontrol.data.model.PaymentData;
import com.azoubel.expensecontrol.data.model.StoreData;
import com.azoubel.expensecontrol.data.model.UserData.PersonData;
import com.azoubel.expensecontrol.data.model.UserData.UserData;
import com.azoubel.expensecontrol.data.AppDatabase;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeController extends BuilderController{

    public HomeController(){}

    /*public void addUser(final Context context, String name, String phoneNumber, byte sex, int image) {
        final UserData userData = new UserData();
        userData.setImage(image);
        AppDatabase.getInstance(context).userDAO().insertAll(userData);
    }*/

    public void addPerson(Context context, String firstName, String phoneNumber, int sex, int image) {
        PersonData personData = new PersonData();
        personData.setFirstName(firstName);
        personData.setPhoneNumber(phoneNumber);
        personData.setSex(sex);
        personData.setImage(image);
        AppDatabase.getInstance(context).userDAO().insertAll(personData);
    }

    public void addPet() {

    }

    public void addHouse() {

    }

    public void addCar() {

    }

    public List<User> loadUsers(final Context context) {
        List<PersonData> personDataList = AppDatabase.getInstance(context).userDAO().getAllPersons();
        List<UserData> userDataList = new ArrayList<>();
        userDataList.addAll(personDataList);
        return buildUsers(context, userDataList);
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

    public void addPayment(Context context, int userId, int expenseId, PaymentWay paymentWay, float value, String creditCardNumber) {
        PaymentData paymentData = new PaymentData();
        paymentData.setPaymentDate(new Date().getTime());
        paymentData.setCreditCardNumber(creditCardNumber);
        paymentData.setExpenseId(expenseId);
        paymentData.setUserId(userId);
        paymentData.setValue(value);
        paymentData.setPaymentWay(paymentWay.name());
        AppDatabase.getInstance(context).paymentDAO().insertPayment(paymentData);
        ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(expenseId);
        expenseData.setLastPaymentDate(new Date().getTime());
        AppDatabase.getInstance(context).expenseDAO().update(expenseData);
    }

    public List<Payment> findPaymentsByExpense(Context context, int expenseId) {
        List<PaymentData> paymentDataList = AppDatabase.getInstance(context).paymentDAO().findPaymentsByExpense(expenseId);
        List<Payment> payments = buildPayments(context, paymentDataList);
        return payments;
    }

    public List<Expense> findExpenseByUser(Context context, int userId, long startDate, long endDate) {
        List<ExpenseData> expenseDataList = AppDatabase.getInstance(context).expenseDAO().findByUser(userId, startDate, endDate);
        return buildExpenses(context, expenseDataList);
    }

    public void addAddress(Context context, String street, int number, String neighborhood, String city, String state, String country, String zipCpde) {
        AddressData addressData = new AddressData();
        addressData.setStreet(street);
        addressData.setNumber(number);
        addressData.setNeighborhood(neighborhood);
        addressData.setCity(city);
        addressData.setState(state);
        addressData.setCountry(country);
        addressData.setZipCode(zipCpde);
        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
    }

    public void addStore(Context context, String storeName, int addressId, String site, String description) {
        StoreData storeData = new StoreData();
        storeData.setStoreName(storeName);
        storeData.setAddressId(addressId);
        storeData.setSite(site);
        storeData.setDescription(description);
        AppDatabase.getInstance(context).storeDAO().insertStore(storeData);
    }

    private List<User> buildUsers(Context context, List<UserData> userDataList) {
        List<User> userList = new ArrayList<>();
        for (UserData userData : userDataList) {
            User user = buildUser(context, userData);
            userList.add(user);
        }
        return userList;
    }




}
