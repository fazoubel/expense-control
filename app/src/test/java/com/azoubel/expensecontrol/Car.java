package com.azoubel.expensecontrol;

public abstract class Car {

    protected String model;
    protected String plate;
    protected String cor;

    public Car(String model, String plate) {
        this.model = model;
        this.plate = plate;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public abstract int getPotenciaDoMotor();

    @Override
    public String toString() {
        return "tem 4 rodas, 1 motor";
    }

    public static void printqq() {
        System.out.println("método statico!!!");
    }
}
