package com.azoubel.expensecontrol;

public class Convertible extends Car{
    public Convertible(String model, String plate) {
        super(model, plate);
    }

    @Override
    public int getPotenciaDoMotor() {
        return 150;
    }

    public String toString() {
        return super.toString() + " abertno em cima, mala atrás, abertura do teto automatica";
    }
}
