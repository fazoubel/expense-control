package com.azoubel.expensecontrol.controller;

import android.content.Context;

import com.azoubel.expensecontrol.data.model.AddressData;
import com.azoubel.expensecontrol.data.model.CreditCardData;
import com.azoubel.expensecontrol.data.model.ExpenseData;
import com.azoubel.expensecontrol.data.model.PaymentData;
import com.azoubel.expensecontrol.data.model.StoreData;
import com.azoubel.expensecontrol.data.model.UserData;
import com.azoubel.expensecontrol.data.AppDatabase;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.Payment;
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

    public void addUser(final Context context, String name, String phoneNumber, byte sex, int image) {
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

    public void addPayment(Context context, int userId, int expenseId, PaymentWay paymentWay, float value, String creditCardNumber) {
        PaymentData paymentData = new PaymentData();
        paymentData.setCreditCardNumber(creditCardNumber);
        paymentData.setExpenseId(expenseId);
        paymentData.setUserId(userId);
        paymentData.setValue(value);
        paymentData.setPaymentWay(paymentWay.name());
        AppDatabase.getInstance(context).paymentDAO().insertPayment(paymentData);
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

    public void addAddress(Context context, String street, int number, String city, String state, String country, String zipCpde) {
        AddressData addressData = new AddressData();
        addressData.setStreet(street);
        addressData.setNumber(number);
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

    private List<Expense> buildExpenses(Context context, List<ExpenseData> expenseDataList) {
        List<Expense> expenseList = new ArrayList<>();
        for (ExpenseData expenseData : expenseDataList) {
            Expense expense = buildExpense(context, expenseData);
            expenseList.add(expense);
        }

        return expenseList;
    }

    private Expense buildExpense(Context context, ExpenseData expenseData) {
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

        if(expenseData.getLastPaymentDate() != null) {
            Date lastPaymentDate = new Date(expenseData.getLastPaymentDate());
            expense.setLastPaymentDate(lastPaymentDate);
        }

        expense.setAssessment(expenseData.getAssessment());

        return expense;
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

    private List<Payment> buildPayments(Context context, List<PaymentData> paymentDataList) {
        List<Payment> paymentList = new ArrayList<>();
        for (PaymentData paymentData : paymentDataList) {
            Payment payment = buildPayment(context, paymentData);
            paymentList.add(payment);
        }
        return paymentList;
    }

    private Payment buildPayment(Context context, PaymentData paymentData) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentData.getPaymentId());

        CreditCardData creditCardData = AppDatabase.getInstance(context).creditCardDAO().getCreditCard(paymentData.getCreditCardNumber());
        CreditCard creditCard = buildCreditCard(context, creditCardData);
        payment.setCreditCard(creditCard);

        ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(paymentData.getExpenseId());
        Expense expense = buildExpense(context, expenseData);
        payment.setExpense(expense);

        payment.setPaymentWay(PaymentWay.valueOf(paymentData.getPaymentWay()));

        payment.setValue(paymentData.getValue());

        UserData userData = AppDatabase.getInstance(context).userDAO().getUser(paymentData.getUserId());
        User user = buildUser(userData);
        payment.setUser(user);

        return payment;
    }

    private CreditCard buildCreditCard(Context context, CreditCardData creditCardData) {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(creditCard.getNumber());

        UserData userData = AppDatabase.getInstance(context).userDAO().getUser(creditCardData.getUserId());
        User user = buildUser(userData);
        creditCard.setUser(user);

        creditCard.setExpiration_date(creditCardData.getExpiration_date());
        creditCard.setFlag(creditCardData.getFlag());
        return creditCard;
    }
}
