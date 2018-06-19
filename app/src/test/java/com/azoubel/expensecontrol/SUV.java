package com.azoubel.expensecontrol;

public class SUV extends Car{

    private int height;

    public SUV(String model, String plate) {
        super(model, plate);
    }

    @Override
    public int getPotenciaDoMotor() {
        return 100;
    }

    @Override
    public String toString() {
        return super.toString() +  " fechado em cima, mala atrás, não abre no teto, não tem bunda";
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
