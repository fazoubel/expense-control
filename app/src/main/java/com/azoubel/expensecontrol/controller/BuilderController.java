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

    protected List<User> buildUsers(Context context, List<UserData> userDataList) {
        List<User> userList = new ArrayList<>();
        for (UserData userData : userDataList) {
            User user = buildUser(context, userData);
            userList.add(user);
        }
        return userList;
    }

    protected User buildUser(Context context, UserData userData) {

        User user;

        if(userData instanceof PersonData) {
            user = buildPerson(context, (PersonData) userData);
        }
        else  if(userData instanceof PetData) {
            user = buildPet(context, (PetData) userData);
        }
        else if(userData instanceof CarData) {
            user = buildCar(context, (CarData) userData);
        }
        else {
            user = buildHouse(context, (HouseData) userData);
        }

        user.setUserId(userData.getUserId());

        user.setImage(userData.getImage());

        user.setExpectedExpensesValue(userData.getExpectedExpensesValue());

        return user;
    }

    protected Person buildPerson(Context context, PersonData personData) {

        Person person = new Person();

        person.setUserId(personData.getUserId());

        Date birthday = new Date(personData.getBirthday());

        person.setFirstName(personData.getFirstName());
        person.setLastName(personData.getLastName());
        person.setNickName(personData.getNickName());
        person.setPhoneNumber(personData.getPhoneNumber());
        person.setSex(personData.getSex());
        person.setBirthday(birthday);

        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(personData.getAddressId());
        if(addressData != null) {
            person.setAddress(buildAddress(addressData));
        }

        return person;
    }

    protected Pet buildPet(Context context, PetData petData) {

        Pet pet = new Pet();

        pet.setUserId(petData.getUserId());
        pet.setAge(petData.getAge());
        pet.setBreed(petData.getBreed());
        pet.setKind(petData.getKind());
        pet.setName(petData.getName());
        pet.setNickName(petData.getNickName());
        pet.setSex(petData.getSex());
        pet.setImage(petData.getImage());
        pet.setExpectedExpensesValue(petData.getExpectedExpensesValue());

        PersonData ownerData = AppDatabase.getInstance(context).userDAO().getPerson(petData.getOwnerId());
        if(ownerData != null) {
            pet.setOwner(buildPerson(context, ownerData));
        }

        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(petData.getAddressId());
        if(addressData != null) {
            pet.setAddress(buildAddress(addressData));
        }

        return pet;
    }

    protected Car buildCar(Context context, CarData carData) {

        Car car = new Car();

        car.setUserId(carData.getUserId());
        car.setModel(carData.getModel());
        car.setBrand(carData.getBrand());
        car.setColor(carData.getColor());
        car.setPlateNumber(carData.getPlateNumber());
        car.setType(carData.getType());
        car.setYear(carData.getYear());
        car.setImage(carData.getImage());
        car.setExpectedExpensesValue(carData.getExpectedExpensesValue());

        PersonData ownerData = AppDatabase.getInstance(context).userDAO().getPerson(carData.getOwnerId());
        if(ownerData != null) {
            car.setOwner(buildPerson(context, ownerData));
        }

        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(carData.getAddressId());
        if(addressData != null) {
            car.setAddress(buildAddress(addressData));
        }


        return car;
    }

    protected House buildHouse(Context context, HouseData houseData) {

        House house = new House();

        house.setUserId(houseData.getUserId());
        house.setType(houseData.getType());
        house.setDescription(houseData.getDescription());
        house.setSquare(houseData.getSquare());
        house.setGarages(houseData.getGarages());
        house.setRooms(houseData.getRooms());
        house.setIsRented(houseData.getIsRented());
        house.setImage(houseData.getImage());
        house.setExpectedExpensesValue(house.getExpectedExpensesValue());

        PersonData tenantData = AppDatabase.getInstance(context).userDAO().getPerson(houseData.getTenantId());
        if(tenantData != null) {
            house.setTenant(buildPerson(context, tenantData));
        }

        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(houseData.getAddressId());
        if(addressData != null) {
            house.setAddress(buildAddress(addressData));
        }

        return house;
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

        PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(expenseData.getUserId());
        Person person = buildPerson(context, personData);

        expense.setBuyer(person);

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

        if(expenseData.getBuyingDate() != null) {
            expense.setExpenseDate(new Date(expenseData.getBuyingDate()));
        }

        return expense;
    }

    protected Store buildStore(Context context, StoreData storeData) {
        Store store = new Store();
        store.setStoreId(storeData.getStoreId());
        store.setStoreName(storeData.getStoreName());
        store.setSite(storeData.getSite());
        store.setDescription(storeData.getDescription());
        store.setProductType(storeData.getProductType());
        store.setPhoneNumber(storeData.getPhoneNumber());
        store.setEmail(storeData.getEmail());
        store.setManagerName(storeData.getManagerName());
        store.setManagerPhoneNumber(storeData.getManagerPhoneNumber());
        store.setManagerEmail(storeData.getManagerEmail());
        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(storeData.getAddressId());
        if(addressData != null) {
            Address address = buildAddress(addressData);
            store.setAddress(address);
        }
        store.setSite(storeData.getSite());
        return store;
    }

    protected Address buildAddress(AddressData addressData) {
        Address address = new Address();
        address.setAddressId(addressData.getAddressId());
        address.setStreet(addressData.getStreet());
        address.setNeighborhood(addressData.getNeighborhood());
        address.setNumber(addressData.getNumber());
        address.setCity(addressData.getCity());
        address.setState(addressData.getState());
        address.setCountry(addressData.getCountry());
        address.setZipCode(addressData.getZipCode());
        address.setApartment(addressData.getApartment());
        address.setApartmentBlock(addressData.getApartmentBlock());
        address.setComplement(addressData.getComplement());
        address.setReference(addressData.getReference());
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

        if(paymentData.getPaymentDate() != null) {
            payment.setPaymentDate(new Date(paymentData.getPaymentDate()));
        }

        PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(paymentData.getUserId());
        Person person = buildPerson(context, personData);
        payment.setPayer(person);

        return payment;
    }

    protected CreditCard buildCreditCard(Context context, CreditCardData creditCardData) {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(creditCardData.getNumber());

        PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(creditCardData.getUserId());
        Person person = buildPerson(context, personData);
        creditCard.setUser(person);

        creditCard.setExpiration_date(creditCardData.getExpiration_date());
        creditCard.setFlag(creditCardData.getFlag());
        return creditCard;
    }

}
