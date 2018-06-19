package com.azoubel.expensecontrol;

import java.util.ArrayList;

public class CarFactory implements PaintCar{

    private static int carsCount;

    private ArrayList<Car> carsList = new ArrayList();

    private ArrayList<OnCreateCarListener> carListeners = new ArrayList();

    public SUV createSUV(String model, String plate) {
        SUV newSUV = new SUV(model, plate);
        this.carsList.add(newSUV);
        carsCount++;
        for (OnCreateCarListener carListener : carListeners) {
            carListener.onCarCreated(newSUV);
        }
        return newSUV;
    }

    public Convertible createConvertible(String model, String plate) {
        Convertible newConvertible = new Convertible(model, plate);
        this.carsList.add(newConvertible);
        carsCount++;
        for (OnCreateCarListener carListener : carListeners) {
            carListener.onCarCreated(newConvertible);
        }
        return newConvertible;
    }

    public int getCarsCount() {
        return this.carsCount;
    }

    public void printAllCars() {
        for (Car car : carsList) {
            System.out.println(car.getModel());
        }
    }

    @Override
    public void paint(Car car, String color) {
        car.setCor(color);
    }

    public void addCarListener(OnCreateCarListener onCreateCarListener){
        this.carListeners.add(onCreateCarListener);

    }
}
