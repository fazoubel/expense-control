package com.azoubel.expensecontrol.controller;

import android.content.Context;

import com.azoubel.expensecontrol.data.model.AddressData;
import com.azoubel.expensecontrol.data.model.ExpenseData;
import com.azoubel.expensecontrol.data.model.PaymentData;
import com.azoubel.expensecontrol.data.model.StoreData;
import com.azoubel.expensecontrol.data.model.UserData.PersonData;
import com.azoubel.expensecontrol.data.model.UserData.UserData;
import com.azoubel.expensecontrol.data.AppDatabase;
import com.azoubel.expensecontrol.model.Address;
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

public class Controller extends BuilderController{

    public Controller(){}

    public void addPerson(Context context, String firstName, String lastName, String nickname, String phoneNumber,
                          long birthDate ,int sex, float expenseLimit, int image, Address address) {

        PersonData personData = new PersonData();

        if(address != null) {
            AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
            if(addressData != null) {
                personData.setAddressId(addressData.getAddressId());
            }
            else {
                AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                        .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                personData.setAddressId(insertedAddress.getAddressId());
            }
        }

        personData.setFirstName(firstName);
        personData.setLastName(lastName);
        personData.setNickName(nickname);
        personData.setBirthday(birthDate);
        personData.setExpectedExpensesValue(expenseLimit);
        personData.setPhoneNumber(phoneNumber);
        personData.setSex(sex);
        personData.setImage(image);
        AppDatabase.getInstance(context).userDAO().insertAll(personData);
    }

    public Person getPerson(Context context, int id) {
        Person person = null;
        PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(id);
        if(personData != null) {
            person = buildPerson(context, personData);
        }
        return person;
    }

    public void addPet() {

    }

    public Pet getPet(Context context, int id) {
        return null;
    }

    public void addHouse() {

    }

    public House getHouse() {
        return null;
    }

    public void addCar() {

    }

    public Car getCar() {
        return null;
    }

    public List<User> loadUsers(final Context context) {
        List<PersonData> personDataList = AppDatabase.getInstance(context).userDAO().getAllPersons();
        List<UserData> userDataList = new ArrayList<>();
        userDataList.addAll(personDataList);
        return buildUsers(context, userDataList);
    }

    public void addExpense(Context context, int userId, Store store, float initialValue, long expirationDate, String description,
                           ExpenseCategory expenseCategory, float assessment) {

        ExpenseData expenseData = new ExpenseData();
        expenseData.setUserId(userId);
        expenseData.setInitialValue(initialValue);
        expenseData.setExpirationDate(expirationDate);
        if(store != null) {
            expenseData.setStoreId(store.getStoreId());
        }
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

    public Expense getExpense(Context context, int id) {
        Expense expense = null;
        ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(id);
        if(expenseData != null) {
            expense = buildExpense(context, expenseData);
        }
        return expense;
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


    public Address findAddress(Context context, String street, int number, String neighborhood) {
        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(street, number, neighborhood);
        Address address=null;
        if(addressData != null) {
            address = buildAddress(addressData);
        }
        return address;
    }

    public void addStore(Context context, String storeName, String site, String description, String productType,
                         String phoneNubmer, String email, String managerName, String managerPhoneNumber,
                         String managerEmail, Address address) {
        StoreData storeData = new StoreData();
        storeData.setStoreName(storeName);
        storeData.setSite(site);
        storeData.setDescription(description);
        storeData.setProductType(productType);
        storeData.setPhoneNumber(phoneNubmer);
        storeData.setEmail(email);
        storeData.setManagerName(managerName);
        storeData.setManagerPhoneNumber(managerPhoneNumber);
        storeData.setManagerEmail(managerEmail);
        if(address != null) {
            storeData.setAddressId(address.getAddressId());
        }
        AppDatabase.getInstance(context).storeDAO().insertStore(storeData);
    }

    public Store getStore(Context context, int id) {
        Store store = null;
        StoreData storeData = AppDatabase.getInstance(context).storeDAO().getStore(id);
        if(storeData != null) {
            store = buildStore(context, storeData);
        }
        return store;
    }

    public List<Store> getAllStores(Context context) {
        List<StoreData> storeDataList = AppDatabase.getInstance(context).storeDAO().getAll();
        List<Store> storeList = new ArrayList<>();
        if(!storeDataList.isEmpty()) {
            for (StoreData storeData : storeDataList) {
                storeList.add(buildStore(context, storeData));
            }
        }
        return storeList;
    }

}
