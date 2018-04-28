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
    /*@Insert
    void insertAll(UserData... userData);

    @Update
    void update(UserData userData);

    @Query("SELECT * FROM UserData")
    List<UserData> getAll();

    @Query("SELECT * FROM UserData WHERE userId = :userId")
    UserData getUser(int userId);

    @Delete
    void delete(UserData userData);*/

    //person
    @Insert
    void insertAll(PersonData... personData);

    @Update
    void update(PersonData personData);

    @Query("SELECT * FROM PersonData")
    List<PersonData> getAllPersons();

    @Query("SELECT * FROM PersonData WHERE userId = :userId")
    PersonData getPerson(int userId);

    @Query("SELECT * FROM PersonData WHERE first_name = :firstName AND last_name = :lastName")
    PersonData getPerson(String firstName, String lastName);

    @Delete
    void delete(PersonData personData);

    //pet
    @Insert
    void insertAll(PetData... petData);

    @Update
    void update(PetData petData);

    @Query("SELECT * FROM PetData")
    List<PetData> getAllPets();

    @Query("SELECT * FROM PetData WHERE userId = :userId")
    PetData getPet(int userId);

    @Delete
    void delete(PetData petData);


    //car
    @Insert
    void insertAll(CarData... carData);

    @Update
    void update(CarData carData);

    @Query("SELECT * FROM CarData")
    List<CarData> getAllCars();

    @Query("SELECT * FROM CarData WHERE userId = :userId")
    CarData getCar(int userId);

    @Delete
    void delete(CarData carData);


    //house
    @Insert
    void insertAll(HouseData... houseData);

    @Update
    void update(HouseData houseData);

    @Query("SELECT * FROM HouseData")
    List<HouseData> getAllHouses();

    @Query("SELECT * FROM HouseData WHERE userId = :userId")
    HouseData getHouse(int userId);

    @Delete
    void delete(HouseData houseData);
}
