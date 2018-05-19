package com.azoubel.expensecontrol.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.azoubel.expensecontrol.data.model.UserData.CarData;
import com.azoubel.expensecontrol.data.model.UserData.HouseData;
import com.azoubel.expensecontrol.data.model.UserData.PersonData;
import com.azoubel.expensecontrol.data.model.UserData.PetData;
import com.azoubel.expensecontrol.data.model.UserData.UserData;

import java.util.List;

@Dao
public interface UserDAO {

    /*@Query("SELECT * FROM UserData WHERE name LIKE :name LIMIT 1")
    UserData findByName(String name);*/

    //user
    @Insert
    long insertUser(UserData userData);

    @Update
    void update(UserData userData);

    @Query("SELECT * FROM UserData")
    List<UserData> getAll();

    @Query("SELECT * FROM UserData WHERE id = :userId")
    UserData getUser(long userId);

    @Delete
    void delete(UserData userData);

    //person
    @Insert
    void insertPerson(PersonData... personData);

    @Update
    void updatePerson(PersonData personData);

    @Query("SELECT * FROM PersonData")
    List<PersonData> getAllPersons();

    @Query("SELECT * FROM PersonData WHERE user_id = :userId")
    PersonData getPerson(long userId);

    @Query("SELECT * FROM PersonData WHERE first_name = :firstName AND last_name = :lastName")
    PersonData getPerson(String firstName, String lastName);

    @Delete
    void deletePerson(PersonData personData);

    //pet
    @Insert
    void insertPet(PetData... petData);

    @Update
    void updatePet(PetData petData);

    @Query("SELECT * FROM PetData")
    List<PetData> getAllPets();

    @Query("SELECT * FROM PetData WHERE user_id = :userId")
    PetData getPet(long userId);

    @Delete
    void deletePet(PetData petData);


    //car
    @Insert
    void insertCar(CarData... carData);

    @Update
    void updateCar(CarData carData);

    @Query("SELECT * FROM CarData")
    List<CarData> getAllCars();

    @Query("SELECT * FROM CarData WHERE user_id = :userId")
    CarData getCar(long userId);

    @Delete
    void deleteCar(CarData carData);


    //house
    @Insert
    void insertHouse(HouseData... houseData);

    @Update
    void updateHouse(HouseData houseData);

    @Query("SELECT * FROM HouseData")
    List<HouseData> getAllHouses();

    @Query("SELECT * FROM HouseData WHERE user_id = :userId")
    HouseData getHouse(long userId);

    @Delete
    void deleteHouse(HouseData houseData);
}
