package com.azoubel.expensecontrol;

public class Database {

    private static Database myDatabase;

    private Database() {

    }

    public static Database getDatabase() {
        if(myDatabase == null) {
            myDatabase = new Database();
        }
        return myDatabase;
    }

}
