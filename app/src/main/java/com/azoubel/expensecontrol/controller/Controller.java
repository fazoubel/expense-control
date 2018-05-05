package com.azoubel.expensecontrol.controller;

import android.content.Context;

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
import com.azoubel.expensecontrol.data.AppDatabase;
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

public class Controller extends BuilderController{

    public Controller(){}

    public void addPerson(Context context, Person person) {

        if(person != null) {
            PersonData personData = new PersonData();
            personData.setFirstName(person.getFirstName());
            personData.setLastName(person.getLastName());
            personData.setNickName(person.getNickName());
            if(person.getBirthday() != null) {
                personData.setBirthday(person.getBirthday().getTime());
            }
            personData.setExpectedExpensesValue(person.getExpectedExpensesValue());
            personData.setPhoneNumber(person.getPhoneNumber());
            personData.setSex(person.getSex());
            personData.setImage(person.getImage());

            Address address = person.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    personData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    personData.setAddressId(insertedAddress.getAddressId());
                }
            }

            AppDatabase.getInstance(context).userDAO().insertAll(personData);

        }

    }

    public Person getPerson(Context context, int id) {
        Person person = null;
        PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(id);
        if(personData != null) {
            person = buildPerson(context, personData);
        }
        return person;
    }

    public void updatePerson(Context context, Person person) {
        if(person != null) {
            PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(person.getUserId());
            if(personData != null) {
                personData.setFirstName(person.getFirstName());
                personData.setLastName(person.getLastName());
                personData.setNickName(person.getNickName());
                if(person.getBirthday() != null) {
                    personData.setBirthday(person.getBirthday().getTime());
                }
                personData.setExpectedExpensesValue(person.getExpectedExpensesValue());
                personData.setPhoneNumber(person.getPhoneNumber());
                personData.setSex(person.getSex());
                personData.setImage(person.getImage());

                Address address = person.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        personData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        personData.setAddressId(insertedAddress.getAddressId());
                    }
                }

                AppDatabase.getInstance(context).userDAO().update(personData);

            }
        }
    }

    public void addPet(Context context, Pet pet) {

        if(pet != null) {

            PetData petData = new PetData();

            petData.setAge(pet.getAge());
            petData.setBreed(pet.getBreed());
            petData.setKind(pet.getKind());
            petData.setName(pet.getName());
            petData.setNickName(pet.getNickName());
            petData.setSex(pet.getSex());
            petData.setImage(pet.getImage());
            petData.setExpectedExpensesValue(pet.getExpectedExpensesValue());

            Address address = pet.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    petData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    petData.setAddressId(insertedAddress.getAddressId());
                }
            }

            if(pet.getOwner() != null) {
                petData.setOwnerId(pet.getOwner().getUserId());
            }

            AppDatabase.getInstance(context).userDAO().insertAll(petData);

        }

    }

    public Pet getPet(Context context, int id) {

        Pet pet = null;
        PetData petData = AppDatabase.getInstance(context).userDAO().getPet(id);
        if(petData != null) {
            pet = buildPet(context, petData);
        }
        return pet;
    }

    public void updatePet(Context context, Pet pet) {
        if(pet != null) {

            PetData petData = AppDatabase.getInstance(context).userDAO().getPet(pet.getUserId());
            if(petData != null) {
                petData.setAge(pet.getAge());
                petData.setBreed(pet.getBreed());
                petData.setKind(pet.getKind());
                petData.setName(pet.getName());
                petData.setNickName(pet.getNickName());
                petData.setSex(pet.getSex());
                petData.setImage(pet.getImage());
                petData.setExpectedExpensesValue(pet.getExpectedExpensesValue());

                Address address = pet.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        petData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        petData.setAddressId(insertedAddress.getAddressId());
                    }
                }

                if(pet.getOwner() != null) {
                    petData.setOwnerId(pet.getOwner().getUserId());
                }

                AppDatabase.getInstance(context).userDAO().update(petData);
            }
        }
    }

    public void addHouse(Context context, House house) {
        if(house != null) {
            HouseData houseData = new HouseData();

            houseData.setDescription(house.getDescription());
            houseData.setGarages(house.getGarages());
            houseData.setIsRented(house.getIsRented());
            houseData.setRooms(house.getRooms());
            houseData.setSquare(house.getSquare());
            if(house.getTenant() != null) {
                houseData.setTenantId(house.getTenant().getUserId());
            }
            house.setType(houseData.getType());

            houseData.setImage(house.getImage());
            houseData.setExpectedExpensesValue(house.getExpectedExpensesValue());

            Address address = house.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    houseData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    houseData.setAddressId(insertedAddress.getAddressId());
                }
            }

            AppDatabase.getInstance(context).userDAO().insertAll(houseData);
        }
    }

    public House getHouse(Context context, int id) {
        House house = null;
        HouseData houseData = AppDatabase.getInstance(context).userDAO().getHouse(id);
        if(houseData != null) {
            house = buildHouse(context, houseData);
        }
        return house;
    }

    public void updateHouse(Context context, House house) {
        if(house != null) {
            HouseData houseData = AppDatabase.getInstance(context).userDAO().getHouse(house.getUserId());
            if(houseData != null) {
                houseData.setDescription(house.getDescription());
                houseData.setGarages(house.getGarages());
                houseData.setIsRented(house.getIsRented());
                houseData.setRooms(house.getRooms());
                houseData.setSquare(house.getSquare());
                if(house.getTenant() != null) {
                    houseData.setTenantId(house.getTenant().getUserId());
                }
                house.setType(houseData.getType());

                houseData.setImage(house.getImage());
                houseData.setExpectedExpensesValue(house.getExpectedExpensesValue());

                Address address = house.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        houseData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        houseData.setAddressId(insertedAddress.getAddressId());
                    }
                }

                AppDatabase.getInstance(context).userDAO().update(houseData);
            }
        }
    }

    public void addCar(Context context, Car car) {
        if(car != null) {

            CarData carData = new CarData();

            carData.setBrand(car.getBrand());
            carData.setColor(car.getColor());
            carData.setModel(car.getModel());
            if(car.getOwner() != null) {
                carData.setOwnerId(car.getOwner().getUserId());
            }
            carData.setPlateNumber(car.getPlateNumber());
            carData.setType(car.getType());
            carData.setYear(car.getYear());
            carData.setImage(car.getImage());
            carData.setExpectedExpensesValue(car.getExpectedExpensesValue());

            Address address = car.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    carData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    carData.setAddressId(insertedAddress.getAddressId());
                }
            }

            AppDatabase.getInstance(context).userDAO().insertAll(carData);
        }
    }

    public Car getCar(Context context, int id) {

        Car car = null;
        CarData carData = AppDatabase.getInstance(context).userDAO().getCar(id);
        if(carData != null) {
            car = buildCar(context, carData);
        }
        return car;
    }

    public void updateCar(Context context, Car car) {
        if(car != null) {

            CarData carData = AppDatabase.getInstance(context).userDAO().getCar(car.getUserId());
            if(carData != null) {
                carData.setBrand(car.getBrand());
                carData.setColor(car.getColor());
                carData.setModel(car.getModel());
                if(car.getOwner() != null) {
                    carData.setOwnerId(car.getOwner().getUserId());
                }
                carData.setPlateNumber(car.getPlateNumber());
                carData.setType(car.getType());
                carData.setYear(car.getYear());
                carData.setImage(car.getImage());
                carData.setExpectedExpensesValue(car.getExpectedExpensesValue());

                Address address = car.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        carData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        carData.setAddressId(insertedAddress.getAddressId());
                    }
                }
                AppDatabase.getInstance(context).userDAO().update(carData);
            }
        }
    }

    public List<User> loadAllPersons(Context context) {
        List<PersonData> personDataList = AppDatabase.getInstance(context).userDAO().getAllPersons();
        List<UserData> userDataList = new ArrayList<>();
        userDataList.addAll(personDataList);
        return buildUsers(context, userDataList);
    }

    public List<User> loadAllUsers(Context context) {
        List<PersonData> personDataList = AppDatabase.getInstance(context).userDAO().getAllPersons();
        List<PetData> petDataList = AppDatabase.getInstance(context).userDAO().getAllPets();
        List<CarData> carDataList = AppDatabase.getInstance(context).userDAO().getAllCars();
        List<HouseData> houseDataList = AppDatabase.getInstance(context).userDAO().getAllHouses();
        List<User> users = new ArrayList<>();
        if(personDataList != null && !personDataList.isEmpty()) {
            for (PersonData personData : personDataList) {
                users.add(buildPerson(context, personData));
            }
        }
        if(petDataList != null && !petDataList.isEmpty()) {
            for (PetData petData : petDataList) {
                users.add(buildPet(context, petData));
            }
        }
        if(carDataList != null && !carDataList.isEmpty()) {
            for (CarData carData : carDataList) {
                users.add(buildCar(context, carData));
            }
        }
        if(houseDataList != null && !houseDataList.isEmpty()) {
            for (HouseData houseData : houseDataList) {
                users.add(buildHouse(context, houseData));
            }
        }
        return users;
    }

    public void addExpense(Context context, Expense expense) {
        if(expense != null) {
            ExpenseData expenseData = new ExpenseData();
            Person person = expense.getBuyer();
            Store store = expense.getStore();
            if(person != null && store != null) {
                expenseData.setUserId(person.getUserId());
                expenseData.setStoreId(store.getStoreId());
                expenseData.setInitialValue(expense.getInitialValue());
                if(expense.getExpenseDate() != null) {
                    expenseData.setExpirationDate(expense.getExpirationDate().getTime());
                }

                expenseData.setAssessment(expense.getAssessment());
                ExpenseCategory category = expense.getCategory();
                if(category != null) {
                    expenseData.setCategory(category.name());
                }
                expenseData.setDescription(expense.getDescription());
                if(expense.getExpenseDate() != null) {
                    expenseData.setBuyingDate(expense.getExpenseDate().getTime());
                }
                AppDatabase.getInstance(context).expenseDAO().insertAll(expenseData);
            }
        }
    }

    public void updateExpense(Context context, Expense expense) {

        if(expense != null) {
            ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(expense.getExpenseId());
            Person person = expense.getBuyer();
            Store store = expense.getStore();
            if(person != null && store != null) {
                expenseData.setUserId(person.getUserId());
                expenseData.setStoreId(store.getStoreId());
                expenseData.setInitialValue(expense.getInitialValue());
                if(expense.getExpenseDate() != null) {
                    expenseData.setExpirationDate(expense.getExpirationDate().getTime());
                }

                expenseData.setAssessment(expense.getAssessment());
                ExpenseCategory category = expense.getCategory();
                if(category != null) {
                    expenseData.setCategory(category.name());
                }
                expenseData.setDescription(expense.getDescription());
                if(expense.getExpenseDate() != null) {
                    expenseData.setBuyingDate(expense.getExpenseDate().getTime());
                }

                AppDatabase.getInstance(context).expenseDAO().update(expenseData);
            }
        }

    }

    public void addPayment(Context context, Payment payment) {
        if(payment != null) {
            PaymentData paymentData = new PaymentData();
            paymentData.setPaymentDate(new Date().getTime());
            CreditCard creditCard = payment.getCreditCard();
            if(creditCard != null) {
                paymentData.setCreditCardNumber(creditCard.getNumber());
            }
            Expense expense = payment.getExpense();
            Person payer = payment.getPayer();
            if(expense != null && payer != null) {
                paymentData.setExpenseId(payment.getExpense().getExpenseId());
                if(payer != null) {
                    paymentData.setUserId(payer.getUserId());
                }
                paymentData.setValue(payment.getValue());
                PaymentWay paymentWay = payment.getPaymentWay();
                if(paymentWay != null) {
                    paymentData.setPaymentWay(paymentWay.name());
                }

                AppDatabase.getInstance(context).paymentDAO().insertPayment(paymentData);
                ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(expense.getExpenseId());
                expenseData.setLastPaymentDate(new Date().getTime());
                AppDatabase.getInstance(context).expenseDAO().update(expenseData);
            }

        }
    }

    public void updatePayment(Context context, Payment payment) {
        if(payment != null) {
            PaymentData paymentData = AppDatabase.getInstance(context).paymentDAO().getPayment(payment.getPaymentId());
            paymentData.setPaymentDate(new Date().getTime());
            CreditCard creditCard = payment.getCreditCard();
            if(creditCard != null) {
                paymentData.setCreditCardNumber(creditCard.getNumber());
            }
            Expense expense = payment.getExpense();
            Person payer = payment.getPayer();
            if(expense != null && payer != null) {
                paymentData.setExpenseId(payment.getExpense().getExpenseId());
                if(payer != null) {
                    paymentData.setUserId(payer.getUserId());
                }
                paymentData.setValue(payment.getValue());
                PaymentWay paymentWay = payment.getPaymentWay();
                if(paymentWay != null) {
                    paymentData.setPaymentWay(paymentWay.name());
                }

                AppDatabase.getInstance(context).paymentDAO().update(paymentData);
                ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(expense.getExpenseId());
                expenseData.setLastPaymentDate(new Date().getTime());
                AppDatabase.getInstance(context).expenseDAO().update(expenseData);
            }

        }
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

    public void addAddress(Context context, Address address) {
        if(address != null) {
            AddressData addressData = new AddressData();
            addressData.setStreet(address.getStreet());
            addressData.setNumber(address.getNumber());
            addressData.setNeighborhood(address.getNeighborhood());
            addressData.setCity(address.getCity());
            addressData.setState(address.getState());
            addressData.setCountry(address.getCountry());
            addressData.setZipCode(address.getZipCode());
            addressData.setReference(address.getReference());
            addressData.setComplement(address.getComplement());
            addressData.setApartment(address.getApartment());
            addressData.setApartmentBlock(address.getApartmentBlock());
            AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
        }
    }

    public void updateAddress(Context context, Address address) {
        if(address != null) {
            AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getAddressId());
            if(addressData != null) {
                addressData.setStreet(address.getStreet());
                addressData.setNumber(address.getNumber());
                addressData.setNeighborhood(address.getNeighborhood());
                addressData.setCity(address.getCity());
                addressData.setState(address.getState());
                addressData.setCountry(address.getCountry());
                addressData.setZipCode(address.getZipCode());
                addressData.setReference(address.getReference());
                addressData.setComplement(address.getComplement());
                addressData.setApartment(address.getApartment());
                addressData.setApartmentBlock(address.getApartmentBlock());
                AppDatabase.getInstance(context).addressDAO().update(addressData);
            }
        }
    }


    public Address findAddress(Context context, String street, int number, String neighborhood) {
        AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(street, number, neighborhood);
        Address address=null;
        if(addressData != null) {
            address = buildAddress(addressData);
        }
        return address;
    }

    public void addStore(Context context, Store store) {
        StoreData storeData = new StoreData();
        storeData.setStoreName(store.getStoreName());
        storeData.setSite(store.getSite());
        storeData.setDescription(store.getDescription());
        storeData.setProductType(store.getProductType());
        storeData.setPhoneNumber(store.getPhoneNumber());
        storeData.setEmail(store.getEmail());
        storeData.setManagerName(store.getManagerName());
        storeData.setManagerPhoneNumber(store.getManagerPhoneNumber());
        storeData.setManagerEmail(store.getManagerEmail());
        Address address = store.getAddress();
        if(address != null) {
            storeData.setAddressId(address.getAddressId());
            updateAddress(context, address);
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

    public void updateStore(Context context, Store store) {
        if(store != null) {
            StoreData storeData = AppDatabase.getInstance(context).storeDAO().getStore(store.getStoreId());
            if(storeData != null) {
                storeData.setStoreName(store.getStoreName());
                storeData.setSite(store.getSite());
                storeData.setDescription(store.getDescription());
                storeData.setProductType(store.getProductType());
                storeData.setPhoneNumber(store.getPhoneNumber());
                storeData.setEmail(store.getEmail());
                storeData.setManagerName(store.getManagerName());
                storeData.setManagerPhoneNumber(store.getManagerPhoneNumber());
                storeData.setManagerEmail(store.getManagerEmail());
                Address address = store.getAddress();
                if(address != null) {
                    storeData.setAddressId(address.getAddressId());
                    updateAddress(context, address);
                }
                AppDatabase.getInstance(context).storeDAO().update(storeData);

            }
        }
    }

    public Payment getPayment(Context context, int id) {
        PaymentData paymentData = AppDatabase.getInstance(context).paymentDAO().getPayment(id);
        return buildPayment(context, paymentData);


    }

    public List<CreditCard> getCreditCardsOfUser(Context context, Person person) {
        List<CreditCard> creditCardList = new ArrayList<>();
        if(person != null) {
            List<CreditCardData> creditCardDataList = AppDatabase.getInstance(context).creditCardDAO().getCreditCardByUser(person.getUserId());
            for (CreditCardData creditCardData : creditCardDataList) {
                creditCardList.add(buildCreditCard(context, creditCardData));
            }
        }
        return creditCardList;
    }

    public void addCreditCard(Context context, CreditCard creditCard) {
        if(creditCard != null && creditCard.getUser() != null) {
            CreditCardData creditCardData = new CreditCardData();
            creditCardData.setUserId(creditCard.getUser().getUserId());
            creditCardData.setExpiration_date(creditCard.getExpiration_date());
            creditCardData.setFlag(creditCard.getFlag());
            creditCardData.setNumber(creditCard.getNumber());
            AppDatabase.getInstance(context).creditCardDAO().insertAll(creditCardData);
        }
    }

    public CreditCard getCreditCard(Context context, String number) {
        CreditCard creditCard=null;
        CreditCardData creditCardData = AppDatabase.getInstance(context).creditCardDAO().getCreditCard(number);
        if(creditCardData != null) {
            creditCard = buildCreditCard(context, creditCardData);
        }
        return creditCard;
    }

}
