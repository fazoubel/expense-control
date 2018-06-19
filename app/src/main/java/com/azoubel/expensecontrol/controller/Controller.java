package com.azoubel.expensecontrol.controller;

import android.content.Context;

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
import com.azoubel.expensecontrol.data.AppDatabase;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.Promotion;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.model.User.Car;
import com.azoubel.expensecontrol.model.User.House;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.Pet;
import com.azoubel.expensecontrol.model.User.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Controller extends BuilderController{

    public Controller(){}

    public void addUser(Context context, User user) {
        if(user instanceof Person) {
            addPerson(context, (Person) user);
        }
        else if (user instanceof Pet) {
            addPet(context, (Pet) user);
        }
        else if(user instanceof House) {
            addHouse(context, (House) user);
        }
        else {
            addCar(context, (Car) user);
        }
    }

    public void updateUser(Context context, User user) {
        if(user instanceof Person) {
            updatePerson(context, (Person) user);
        }
        else if (user instanceof Pet) {
            updatePet(context, (Pet) user);
        }
        else if(user instanceof House) {
            updateHouse(context, (House) user);
        }
        else {
            updateCar(context, (Car) user);
        }
    }

    public void addPerson(Context context, Person person) {

        if(person != null) {
            UserData userData = new UserData();
            PersonData personData = new PersonData();
            personData.setFirstName(person.getFirstName());
            personData.setLastName(person.getLastName());
            personData.setNickName(person.getNickName());
            personData.setBirthday(person.getBirthday());
            userData.setExpectedExpensesValue(person.getExpectedExpensesValue());
            personData.setPhoneNumber(person.getPhoneNumber());
            personData.setSex(person.getSex());
            userData.setImage(person.getImage());

            Address address = person.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    userData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    userData.setAddressId(insertedAddress.getAddressId());
                }
            }

            long id = AppDatabase.getInstance(context).userDAO().insertUser(userData);
            personData.setUserId(id);
            AppDatabase.getInstance(context).userDAO().insertPerson(personData);

        }

    }

    public User getUser(Context context, long id) {
        Person person = getPerson(context, id);
        if(person != null) {
            return person;
        }
        else {
            Pet pet = getPet(context, id);
            if (pet != null) {
                return pet;
            }
            else {
                House house = getHouse(context, id);
                if(house != null) {
                    return house;
                }
                else {
                    Car car = getCar(context, id);
                    return car;
                }
            }
        }

    }

    public Person getPerson(Context context, long id) {
        Person person = null;
        UserData userData = AppDatabase.getInstance(context).userDAO().getUser(id);
        PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(id);
        if(personData != null) {
            person = buildPerson(context, personData, userData);
        }
        return person;
    }

    public void updatePerson(Context context, Person person) {
        if(person != null) {
            UserData userData = AppDatabase.getInstance(context).userDAO().getUser(person.getUserId());
            PersonData personData = AppDatabase.getInstance(context).userDAO().getPerson(person.getUserId());
            if(personData != null) {
                personData.setFirstName(person.getFirstName());
                personData.setLastName(person.getLastName());
                personData.setNickName(person.getNickName());
                personData.setBirthday(person.getBirthday());
                userData.setExpectedExpensesValue(person.getExpectedExpensesValue());
                personData.setPhoneNumber(person.getPhoneNumber());
                personData.setSex(person.getSex());
                userData.setImage(person.getImage());

                Address address = person.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getAddressId());
                    if(addressData != null) {
                        userData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        userData.setAddressId(insertedAddress.getAddressId());
                    }
                }

                AppDatabase.getInstance(context).userDAO().update(userData);
                AppDatabase.getInstance(context).userDAO().updatePerson(personData);

            }
        }
    }

    public void addPet(Context context, Pet pet) {

        if(pet != null) {

            UserData userData = new UserData();
            PetData petData = new PetData();

            petData.setAge(pet.getAge());
            petData.setBreed(pet.getBreed());
            petData.setSpecies(pet.getSpecies());
            petData.setName(pet.getName());
            petData.setNickName(pet.getNickName());
            petData.setSex(pet.getSex());
            userData.setImage(pet.getImage());
            userData.setExpectedExpensesValue(pet.getExpectedExpensesValue());

            Address address = pet.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    userData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    userData.setAddressId(insertedAddress.getAddressId());
                }
            }

            if(pet.getOwner() != null) {
                petData.setOwnerId(pet.getOwner().getUserId());
            }

            long id = AppDatabase.getInstance(context).userDAO().insertUser(userData);
            petData.setUserId(id);
            AppDatabase.getInstance(context).userDAO().insertPet(petData);
        }

    }

    public Pet getPet(Context context, long id) {

        Pet pet = null;
        UserData userData = AppDatabase.getInstance(context).userDAO().getUser(id);
        PetData petData = AppDatabase.getInstance(context).userDAO().getPet(id);
        if(petData != null) {
            pet = buildPet(context, petData, userData);
        }
        return pet;
    }

    public void updatePet(Context context, Pet pet) {
        if(pet != null) {

            UserData userData = AppDatabase.getInstance(context).userDAO().getUser(pet.getUserId());
            PetData petData = AppDatabase.getInstance(context).userDAO().getPet(pet.getUserId());
            if(petData != null) {
                petData.setAge(pet.getAge());
                petData.setBreed(pet.getBreed());
                petData.setSpecies(pet.getSpecies());
                petData.setName(pet.getName());
                petData.setNickName(pet.getNickName());
                petData.setSex(pet.getSex());
                userData.setImage(pet.getImage());
                userData.setExpectedExpensesValue(pet.getExpectedExpensesValue());

                Address address = pet.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        userData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        userData.setAddressId(insertedAddress.getAddressId());
                    }
                }

                if(pet.getOwner() != null) {
                    petData.setOwnerId(pet.getOwner().getUserId());
                }

                AppDatabase.getInstance(context).userDAO().update(userData);
                AppDatabase.getInstance(context).userDAO().updatePet(petData);
            }
        }
    }

    public void addHouse(Context context, House house) {
        if(house != null) {
            UserData userData = new UserData();
            HouseData houseData = new HouseData();

            houseData.setDescription(house.getDescription());
            houseData.setGarages(house.getGarages());
            houseData.setIsRented(house.getIsRented());
            houseData.setRooms(house.getRooms());
            houseData.setArea(house.getArea());
            if(house.getTenant() != null) {
                houseData.setTenantId(house.getTenant().getUserId());
            }
            house.setType(houseData.getType());

            userData.setImage(house.getImage());
            userData.setExpectedExpensesValue(house.getExpectedExpensesValue());

            Address address = house.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    userData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    userData.setAddressId(insertedAddress.getAddressId());
                }
            }

            long id = AppDatabase.getInstance(context).userDAO().insertUser(userData);
            houseData.setUserId(id);
            AppDatabase.getInstance(context).userDAO().insertHouse(houseData);
        }
    }

    public House getHouse(Context context, long id) {
        House house = null;
        UserData userData = AppDatabase.getInstance(context).userDAO().getUser(id);
        HouseData houseData = AppDatabase.getInstance(context).userDAO().getHouse(id);
        if(houseData != null) {
            house = buildHouse(context, houseData, userData);
        }
        return house;
    }

    public void updateHouse(Context context, House house) {
        if(house != null) {
            UserData userData = AppDatabase.getInstance(context).userDAO().getUser(house.getUserId());
            HouseData houseData = AppDatabase.getInstance(context).userDAO().getHouse(house.getUserId());
            if(houseData != null) {
                houseData.setDescription(house.getDescription());
                houseData.setGarages(house.getGarages());
                houseData.setIsRented(house.getIsRented());
                houseData.setRooms(house.getRooms());
                houseData.setArea(house.getArea());
                if(house.getTenant() != null) {
                    houseData.setTenantId(house.getTenant().getUserId());
                }
                houseData.setType(house.getType());

                userData.setImage(house.getImage());
                userData.setExpectedExpensesValue(house.getExpectedExpensesValue());

                Address address = house.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        userData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        userData.setAddressId(insertedAddress.getAddressId());
                    }
                }

                AppDatabase.getInstance(context).userDAO().update(userData);
                AppDatabase.getInstance(context).userDAO().updateHouse(houseData);
            }
        }
    }

    public void addCar(Context context, Car car) {
        if(car != null) {

            UserData userData = new UserData();
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
            userData.setImage(car.getImage());
            userData.setExpectedExpensesValue(car.getExpectedExpensesValue());

            Address address = car.getAddress();

            if(address != null) {
                AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                if(addressData != null) {
                    userData.setAddressId(addressData.getAddressId());
                    updateAddress(context, address);
                }
                else {
                    AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                    AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                            .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    userData.setAddressId(insertedAddress.getAddressId());
                }
            }

            long id = AppDatabase.getInstance(context).userDAO().insertUser(userData);
            carData.setUserId(id);
            AppDatabase.getInstance(context).userDAO().insertCar(carData);
        }
    }

    public Car getCar(Context context, long id) {

        Car car = null;
        UserData userData = AppDatabase.getInstance(context).userDAO().getUser(id);
        CarData carData = AppDatabase.getInstance(context).userDAO().getCar(id);
        if(carData != null) {
            car = buildCar(context, carData, userData);
        }
        return car;
    }

    public void updateCar(Context context, Car car) {
        if(car != null) {

            UserData userData = AppDatabase.getInstance(context).userDAO().getUser(car.getUserId());
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
                userData.setImage(car.getImage());
                userData.setExpectedExpensesValue(car.getExpectedExpensesValue());

                Address address = car.getAddress();

                if(address != null) {
                    AddressData addressData = AppDatabase.getInstance(context).addressDAO().getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                    if(addressData != null) {
                        userData.setAddressId(addressData.getAddressId());
                        updateAddress(context, address);
                    }
                    else {
                        AppDatabase.getInstance(context).addressDAO().insertAddress(addressData);
                        AddressData insertedAddress = AppDatabase.getInstance(context).addressDAO()
                                .getAddress(address.getStreet(), address.getNumber(), address.getNeighborhood());
                        userData.setAddressId(insertedAddress.getAddressId());
                    }
                }
                AppDatabase.getInstance(context).userDAO().update(userData);
                AppDatabase.getInstance(context).userDAO().updateCar(carData);
            }
        }
    }

    public List<User> loadAllPersons(Context context) {
        List<PersonData> personDataList = AppDatabase.getInstance(context).userDAO().getAllPersons();
        List<User> personList = new ArrayList<>();
        if(personDataList != null && !personDataList.isEmpty()) {
            for (PersonData personData : personDataList) {
                UserData userData= AppDatabase.getInstance(context).userDAO().getUser(personData.getUserId());
                personList.add(buildPerson(context, personData, userData));
            }
        }

        return personList;
    }

    public List<User> loadAllUsers(Context context) {
        List<PersonData> personDataList  = AppDatabase.getInstance(context).userDAO().getAllPersons();
        List<PetData> petDataList = AppDatabase.getInstance(context).userDAO().getAllPets();
        List<CarData> carDataList = AppDatabase.getInstance(context).userDAO().getAllCars();
        List<HouseData> houseDataList = AppDatabase.getInstance(context).userDAO().getAllHouses();
        List<User> users = new ArrayList<>();

        if(personDataList != null && !personDataList.isEmpty()) {
            for (PersonData personData: personDataList) {
                UserData userData= AppDatabase.getInstance(context).userDAO().getUser(personData.getUserId());
                users.add(buildPerson(context, personData, userData));
            }
        }
        if(petDataList != null && !petDataList.isEmpty()) {
            for (PetData petData : petDataList) {
                UserData userData= AppDatabase.getInstance(context).userDAO().getUser(petData.getUserId());
                users.add(buildPet(context, petData, userData));
            }
        }
        if(houseDataList != null && !houseDataList.isEmpty()) {
            for (HouseData houseData : houseDataList) {
                UserData userData= AppDatabase.getInstance(context).userDAO().getUser(houseData.getUserId());
                users.add(buildHouse(context, houseData, userData));
            }
        }
        if(carDataList != null && !carDataList.isEmpty()) {
            for (CarData carData : carDataList) {
                UserData userData= AppDatabase.getInstance(context).userDAO().getUser(carData.getUserId());
                users.add(buildCar(context, carData, userData));
            }
        }

        return users;
    }

    public void addExpense(Context context, Expense expense) {
        if(expense != null) {
            ExpenseData expenseData = new ExpenseData();
            Person person = expense.getBuyer();
            Store store = expense.getStore();
            if(person != null) {
                expenseData.setUserId(person.getUserId());
                if(store != null) {
                    expenseData.setStoreId(store.getStoreId());
                }

                expenseData.setInitialValue(expense.getInitialValue());
                expenseData.setExpirationDate(expense.getExpirationDate());

                expenseData.setAssessment(expense.getAssessment());
                expenseData.setCategory(expense.getCategory());

                expenseData.setFinalValue(expense.getFinalValue());

                expenseData.setDescription(expense.getDescription());
                expenseData.setBuyingDate(expense.getExpenseDate());

                long id = AppDatabase.getInstance(context).expenseDAO().insert(expenseData);

                List<Promotion> discounts = expense.getDiscountList();

                if(discounts != null && !discounts.isEmpty()) {
                    for (Promotion discount : discounts) {
                        DiscountData discountData = new DiscountData();
                        discountData.setExpenseId(id);
                        discountData.setPromotionId(discount.getPromotionId());
                        AppDatabase.getInstance(context).discountDAO().insertAll(discountData);
                    }
                }
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
                expenseData.setExpirationDate(expense.getExpirationDate());

                expenseData.setAssessment(expense.getAssessment());
                expenseData.setCategory(expense.getCategory());
                expenseData.setDescription(expense.getDescription());
                expenseData.setBuyingDate(expense.getExpenseDate());

                expenseData.setFinalValue(expense.getFinalValue());

                AppDatabase.getInstance(context).discountDAO().deleteDiscounts(expense.getExpenseId());

                List<Promotion> discounts = expense.getDiscountList();

                if(discounts != null && !discounts.isEmpty()) {
                    for (Promotion discount : discounts) {
                        DiscountData discountData = new DiscountData();
                        discountData.setExpenseId(expense.getExpenseId());
                        discountData.setPromotionId(discount.getPromotionId());
                        AppDatabase.getInstance(context).discountDAO().insertAll(discountData);
                    }
                }

                AppDatabase.getInstance(context).expenseDAO().update(expenseData);
            }
        }

    }

    public void addPayment(Context context, Payment payment) {
        if(payment != null) {
            PaymentData paymentData = new PaymentData();
            paymentData.setPaymentDate(payment.getPaymentDate());
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
                paymentData.setPaymentWay(payment.getPaymentWay());

                AppDatabase.getInstance(context).paymentDAO().insertPayment(paymentData);
                ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(expense.getExpenseId());
                AppDatabase.getInstance(context).expenseDAO().update(expenseData);
            }

        }
    }

    public void updatePayment(Context context, Payment payment) {
        if(payment != null) {
            PaymentData paymentData = AppDatabase.getInstance(context).paymentDAO().getPayment(payment.getPaymentId());
            paymentData.setPaymentDate(payment.getPaymentDate());
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
                paymentData.setPaymentWay(payment.getPaymentWay());

                AppDatabase.getInstance(context).paymentDAO().update(paymentData);
                ExpenseData expenseData = AppDatabase.getInstance(context).expenseDAO().getExpense(expense.getExpenseId());
                AppDatabase.getInstance(context).expenseDAO().update(expenseData);
            }

        }
    }

    public List<Payment> findPaymentsByExpense(Context context, long expenseId) {
        List<PaymentData> paymentDataList = AppDatabase.getInstance(context).paymentDAO().findPaymentsByExpense(expenseId);
        List<Payment> payments = buildPayments(context, paymentDataList);
        return payments;
    }

    public List<Expense> findExpenseByUser(Context context, long userId, long startDate, long endDate) {
        List<ExpenseData> expenseDataList = AppDatabase.getInstance(context).expenseDAO().findByUser(userId, startDate, endDate);
        return buildExpenses(context, expenseDataList);
    }

    public List<Expense> findExpenseByUser(Context context, long userId) {
        List<ExpenseData> expenseDataList = AppDatabase.getInstance(context).expenseDAO().findByUser(userId);
        return buildExpenses(context, expenseDataList);
    }

    public Expense getExpense(Context context, long id) {
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

    public void addPromotion(Context context, Promotion promotion) {
        if(promotion != null) {
            PromotionData promotionData = new PromotionData();
            promotionData.setDescription(promotion.getDescription());
            promotionData.setDiscountInPercent(promotion.getDiscountInPercent());
            promotionData.setStartDate(promotion.getStartDate());
            promotionData.setName(promotion.getName());
            promotionData.setEndDate(promotion.getEndDate());
            promotionData.setPaymentWayRestriction(promotion.getPaymentWayRestriction());
            promotionData.setPrize(promotion.getPrize());
            promotionData.setPrizeLotteryDate(promotion.getPrizeLotteryDate());
            promotionData.setPhoneNumber(promotion.getPhoneNumber());
            if(promotion.getStore() != null) {
                promotionData.setStoreId(promotion.getStore().getStoreId());
            }

            AppDatabase.getInstance(context).promotionDAO().insertAll(promotionData);
        }

    }


    public void updatePromotion(Context context, Promotion promotion) {
        if(promotion != null) {
            PromotionData promotionData = AppDatabase.getInstance(context).promotionDAO().getPromotion(promotion.getPromotionId());
            if(promotionData != null) {
                promotionData.setDescription(promotion.getDescription());
                promotionData.setDiscountInPercent(promotion.getDiscountInPercent());
                promotionData.setStartDate(promotion.getStartDate());
                promotionData.setName(promotion.getName());
                promotionData.setEndDate(promotion.getEndDate());
                promotionData.setPaymentWayRestriction(promotion.getPaymentWayRestriction());
                promotionData.setPrize(promotion.getPrize());
                promotionData.setPrizeLotteryDate(promotion.getPrizeLotteryDate());
                promotionData.setPhoneNumber(promotion.getPhoneNumber());
                if(promotion.getStore() != null) {
                    promotionData.setStoreId(promotion.getStore().getStoreId());
                }
                AppDatabase.getInstance(context).promotionDAO().update(promotionData);
            }
        }
    }

    public Promotion getPromotion(Context context, int promotionId) {
        Promotion promotion = null;
        PromotionData promotionData = AppDatabase.getInstance(context).promotionDAO().getPromotion(promotionId);
        if(promotionData != null) {
            promotion = buildPromotion(context, promotionData);
        }
        return  promotion;
    }

    public List<Promotion> getAllPromotionsByStore(Context context, Store store) {
        List<Promotion> allPromotions = new ArrayList<>();
        List<PromotionData> allPromotionByStore = AppDatabase.getInstance(context).promotionDAO().getAllPromotionByStore(store.getStoreId());
        if(allPromotionByStore != null && !allPromotionByStore.isEmpty()) {
            for (PromotionData promotionData : allPromotionByStore) {
                allPromotions.add(buildPromotion(context, promotionData));
            }
        }
        return allPromotions;
    }

    public List<Promotion> getAllPromotions(Context context) {
        List<Promotion> allPromotions = new ArrayList<>();
        List<PromotionData> allPromotionData = AppDatabase.getInstance(context).promotionDAO().getAllPromotions();
        if(allPromotionData != null && !allPromotionData.isEmpty()) {
            for (PromotionData promotionData : allPromotionData) {
                allPromotions.add(buildPromotion(context, promotionData));
            }
        }
        return allPromotions;
    }
    
    public void populateDatabase(Context context) {
        Address address1 = new Address();
        address1.setStreet("rua alemanha");
        address1.setNumber(102);
        address1.setNeighborhood("rio doce");
        address1.setCity("olinda");
        address1.setState("pernambuco");
        address1.setCountry("brazil");
        address1.setZipCode("11111-111");
        //address1.setReference();
        //address1.setApartment();
        //address1.setApartmentBlock();

        addAddress(context, address1);

        Address address2 = new Address();
        address2.setStreet("rua chicago");
        address2.setNumber(2052);
        address2.setNeighborhood("Graças");
        address2.setCity("Recife");
        address2.setState("pernambuco");
        address2.setCountry("brazil");
        address2.setZipCode("22222-222");
        address2.setReference("perto do longe");
        address2.setApartment(1345);
        address2.setApartmentBlock("D");

        addAddress(context, address2);

        Address address3 = new Address();
        address3.setStreet("rua Mexico");
        address3.setNumber(119);
        address3.setNeighborhood("Paris");
        address3.setCity("Paulista");
        address3.setState("pernambuco");
        address3.setCountry("brazil");
        address3.setZipCode("33333-333");
        address3.setApartment(236);
        address3.setApartmentBlock("Norte");

        addAddress(context, address3);

        //addAddress(this, "avenida mexico", 102, "rio doce","paulista", "pernambuco", "brazil", "33333-333");

        address1 = findAddress(context, "rua alemanha", 102, "rio doce");

        address2 = findAddress(context, "rua chicago", 2052, "Graças");

        address3 = findAddress(context, "rua Mexico", 119, "Paris");

        Person person1 = new Person();
        person1.setFirstName("Fabio");
        person1.setLastName("Azoubel");
        person1.setNickName("Fabinho");
        person1.setPhoneNumber("111111111111");
        person1.setBirthday(new Date());
        person1.setSex("masculino");
        person1.setImage(User.IMAGE_BOY);
        person1.setExpectedExpensesValue(500f);
        person1.setAddress(address1);

        addPerson(context, person1);

        Person person2 = new Person();
        person2.setFirstName("João");
        person2.setLastName("Silva");
        person2.setNickName("Joãozinho");
        person2.setPhoneNumber("222222222222");
        person2.setBirthday(new Date());
        person2.setSex("masculino");
        person2.setImage(User.IMAGE_OLD_MAN);
        person2.setExpectedExpensesValue(1500f);
        person2.setAddress(address2);

        addPerson(context, person2);

        Person person3 = new Person();
        person3.setFirstName("Maria");
        person3.setLastName("Pereira");
        person3.setNickName("Mari");
        person3.setPhoneNumber("3333333333");
        person3.setBirthday(new Date());
        person3.setSex("feminino");
        person3.setImage(User.IMAGE_WOMAN);
        person1.setExpectedExpensesValue(5000f);
        person3.setAddress(address3);

        addPerson(context, person3);

        List<User> users = loadAllUsers(context);

        Store store1 = new Store();
        store1.setStoreName("Loja da Casa");
        store1.setSite("lojadacasa.com.br");
        store1.setDescription("Loja de Artigos para Casa");
        store1.setProductType("Casa");
        store1.setPhoneNumber("12321312312");
        store1.setEmail("lojadacasa@gmail.com");
        store1.setManagerName("Olavo");
        store1.setManagerPhoneNumber("123123123");
        store1.setManagerEmail("olavogerente@gmail.com");
        store1.setAddress(address1);

        addStore(context, store1);

        Store store2 = new Store();
        store2.setStoreName("Bompreço");
        store2.setSite("bompreco.com.br");
        store2.setDescription("Mercado");
        store2.setProductType("Comida");
        store2.setPhoneNumber("9999999999999");
        store2.setEmail("bompreco@gmail.com");
        store2.setManagerName("Gilmar");
        store2.setManagerPhoneNumber("44444444444");
        store2.setManagerEmail("gilmargerente@gmail.com");
        store2.setAddress(address2);

        addStore(context, store2);

        Calendar calendar = Calendar.getInstance();

        //calendar.add(Calendar.DATE, 2);

        Date expirationDate = calendar.getTime();

        List<Store> stores = getAllStores(context);

        store1=null;
        store2=null;

        if(!stores.isEmpty() && stores.size() >=2) {
            store1 = stores.get(0);
            store2 = stores.get(1);
        }

        Expense expense1 = new Expense();
        expense1.setBuyer((Person)users.get(0));
        expense1.setStore(store1);
        expense1.setInitialValue(55.04f);
        expense1.setExpenseDate(expirationDate);
        expense1.setExpirationDate(expirationDate);
        expense1.setCategory("compra");
        expense1.setDescription("compra de feijão");
        expense1.setAssessment(0);

        Promotion promotion1 = new Promotion();
        promotion1.setDescription("compre feijão pela metade do preço!");
        promotion1.setDiscountInPercent(50f);
        promotion1.setStartDate(calendar.getTime());
        promotion1.setName("compra de feijão");
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        promotion1.setEndDate(calendar.getTime());
        promotion1.setPaymentWayRestriction("dinheiro");
        promotion1.setPrize("sem prêmio");
        promotion1.setPhoneNumber("(51)000000000");
        promotion1.setStore(store1);

        addPromotion(context, promotion1);

        promotion1 = getPromotion(context ,1);

        Promotion promotion2 = new Promotion();
        promotion2.setDescription("compre uma abobrinha com 20% de desconto!");
        promotion2.setDiscountInPercent(20f);
        calendar = Calendar.getInstance();
        promotion2.setStartDate(calendar.getTime());
        promotion2.setName("compra de abobrinha");
        calendar.add(Calendar.DATE, 10);
        promotion2.setEndDate(calendar.getTime());
        promotion2.setPrize("uma paçoca");
        promotion2.setPhoneNumber("(51)988728631");
        promotion2.setStore(store2);

        addPromotion(context, promotion2);

        promotion2 = getPromotion(context ,2);

        List<Promotion> discountsList = new ArrayList<>();
        discountsList.add(promotion1);
        discountsList.add(promotion2);

        expense1.setDiscountList(discountsList);

        addExpense(context, expense1);

        Date expirationDate2 = calendar.getTime();

        Expense expense2 = new Expense();
        expense2.setBuyer((Person)users.get(1));
        expense2.setStore(store2);
        expense2.setInitialValue(105.00f);
        expense2.setExpenseDate(expirationDate2);
        expense2.setExpirationDate(expirationDate2);
        expense2.setCategory("compra");
        expense2.setDescription("compra de cágado");
        expense2.setAssessment(5.5f);

        addExpense(context, expense2);

        List<Expense> expenseList = findExpenseByUser(context, users.get(0).getUserId());

        Payment payment1 = new Payment();
        payment1.setValue(expenseList.get(0).getInitialValue()/2);
        payment1.setExpense(expenseList.get(0));
        payment1.setPayer((Person)users.get(0));
        payment1.setPaymentWay("dinheiro");
        //payment1.setPaymentDate();
        //payment1.setCreditCard("");

        addPayment(context, payment1);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setFlag("visa");
        creditCard1.setExpiration_date(new Date());
        creditCard1.setNumber("2222-3333-4444-5555");
        creditCard1.setUser(users.get(0));

        addCreditCard(context, creditCard1);

        Payment payment2 = new Payment();
        payment2.setValue(expenseList.get(0).getInitialValue()/2);
        payment2.setExpense(expenseList.get(0));
        payment2.setPayer((Person)users.get(0));
        payment2.setPaymentWay("credito");
        //payment2.setPaymentDate();
        payment2.setCreditCard(creditCard1);

        addPayment(context, payment2);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setFlag("mastercard");
        creditCard2.setExpiration_date(new Date());
        creditCard2.setNumber("1111-3333-7777-5555");
        creditCard2.setUser(users.get(1));

        addCreditCard(context, creditCard2);

        CreditCard creditCard3 = new CreditCard();
        creditCard3.setFlag("visa");
        creditCard3.setExpiration_date(new Date());
        creditCard3.setNumber("6666-3436-7444-5895");
        creditCard3.setUser(users.get(1));

        addCreditCard(context, creditCard3);

        List<Expense> expenseList2 = findExpenseByUser(context, users.get(1).getUserId());

        Payment payment3 = new Payment();
        payment3.setValue(expenseList2.get(0).getInitialValue()/3);
        payment3.setExpense(expenseList2.get(0));
        payment3.setPayer((Person)users.get(0));
        payment3.setPaymentWay("credito");
        //payment3.setPaymentDate();
        payment3.setCreditCard(creditCard1);

        addPayment(context, payment3);

        Payment payment4 = new Payment();
        payment4.setValue(expenseList2.get(0).getInitialValue()/3);
        payment4.setExpense(expenseList2.get(0));
        payment4.setPayer((Person)users.get(1));
        payment4.setPaymentWay("credito");
        //payment4.setPaymentDate();
        payment4.setCreditCard(creditCard2);

        addPayment(context, payment4);

        Payment payment5 = new Payment();
        payment5.setValue(expenseList2.get(0).getInitialValue()/3);
        payment5.setExpense(expenseList2.get(0));
        payment5.setPayer((Person)users.get(1));
        payment5.setPaymentWay("credito");
        //payment5.setPaymentDate();
        payment5.setCreditCard(creditCard3);

        addPayment(context, payment5);



    }

}
