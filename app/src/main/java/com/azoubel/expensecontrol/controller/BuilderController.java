package com.azoubel.expensecontrol.controller;

import android.content.Context;

import com.azoubel.expensecontrol.data.AppDatabase;
import com.azoubel.expensecontrol.data.model.AddressData;
import com.azoubel.expensecontrol.data.model.CreditCardData;
import com.azoubel.expensecontrol.data.model.ExpenseData;
import com.azoubel.expensecontrol.data.model.PaymentData;
import com.azoubel.expensecontrol.data.model.StoreData;
import com.azoubel.expensecontrol.data.model.UserData.CarData;
import com.azoubel.expensecontrol.data.model.UserData.HouseData;
import com.azoubel.expensecontrol.data.model.UserData.PersonData;
import com.azoubel.expensecontrol.data.model.UserData.PetData;
import com.azoubel.expensecontrol.data.model.UserData.UserData;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.model.User.Car;
import com.azoubel.expensecontrol.model.User.House;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.Pet;
import com.azoubel.expensecontrol.model.User.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuilderController {

    protected User buildUser(UserData userData) {

        User user;

        if(userData instanceof PersonData) {
            user = buildPerson((PersonData) userData);
        }
        else  if(userData instanceof PetData) {
            user = buildPet((PetData) userData);
        }
        else if(userData instanceof CarData) {
            user = buildCar((CarData) userData);
        }
        else {
            user = buildHouse((HouseData) userData);
        }

        user.setUserId(userData.getUserId());

        user.setImage(userData.getImage());

        user.setExpectedExpensesValue(userData.getExpectedExpensesValue());

        return user;
    }

    protected Person buildPerson(PersonData personData) {

        Person person = new Person();


        return person;
    }

    protected Pet buildPet(PetData petData) {
        return null;
    }

    protected Car buildCar(CarData carData) {
        return null;
    }

    protected House buildHouse(HouseData houseData) {
        return null;
    }

    protected List<Expense> buildExpenses(Context context, List<ExpenseData> expenseDataList) {
        List<Expense> expenseList = new ArrayList<>();
        for (ExpenseData expenseData : expenseDataList) {
            Expense expense = buildExpense(context, expenseData);
            expenseList.add(expense);
        }

        return expenseList;
    }

    protected Expense buildExpense(Context context, ExpenseData expenseData) {
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

    protected Store buildStore(Context context, StoreData storeData) {
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

    protected Address buildAddress(AddressData addressData) {
        Address address = new Address();
        address.setStreet(addressData.getStreet());
        address.setNumber(addressData.getNumber());
        address.setCity(addressData.getCity());
        address.setState(addressData.getState());
        address.setCountry(addressData.getCountry());
        address.setZipCode(addressData.getZipCode());
        return address;
    }

    protected List<Payment> buildPayments(Context context, List<PaymentData> paymentDataList) {
        List<Payment> paymentList = new ArrayList<>();
        for (PaymentData paymentData : paymentDataList) {
            Payment payment = buildPayment(context, paymentData);
            paymentList.add(payment);
        }
        return paymentList;
    }

    protected Payment buildPayment(Context context, PaymentData paymentData) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentData.getPaymentId());

        CreditCardData creditCardData = AppDatabase.getInstance(context).creditCardDAO().getCreditCard(paymentData.getCreditCardNumber());
        if(creditCardData != null) {
            CreditCard creditCard = buildCreditCard(context, creditCardData);
            payment.setCreditCard(creditCard);
        }

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

    protected CreditCard buildCreditCard(Context context, CreditCardData creditCardData) {
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
