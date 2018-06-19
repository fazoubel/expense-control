package com.azoubel.expensecontrol;

import java.util.ArrayList;

public class Detran implements OnCreateCarListener{

    ArrayList<String> registerList = new ArrayList();

    public void createRegister(Car car) {
        registerList.add("car created_"+car.getModel()+"_"+car.getPlate());
    }

    @Override
    public void onCarCreated(Car car) {
        createRegister(car);
    }

    public void printList(){
        for (String s : registerList) {
            System.out.println(s);
        }
    }
}
