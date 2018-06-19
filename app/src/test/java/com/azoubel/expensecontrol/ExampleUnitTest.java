package com.azoubel.expensecontrol;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        Detran detran = new Detran();

        CarFactory carFactory = new CarFactory();

        carFactory.addCarListener(detran);

        Car rav4 = carFactory.createSUV("RAV4", "ADB3456");

        System.out.println(rav4.getModel());

        System.out.println("count = "+carFactory.getCarsCount());

        Car mustang = carFactory.createConvertible("mustang", "ZZZ3376");

        System.out.println(mustang.getModel());

        System.out.println("count = "+carFactory.getCarsCount());

        CarFactory carFactory2 = new CarFactory();

        carFactory2.addCarListener(detran);

        carFactory2.createSUV("ferrari", "OER3484");

        carFactory.printAllCars();

        carFactory2.printAllCars();

        System.out.println("count = "+carFactory2.getCarsCount());

        detran.printList();

        System.out.println(rav4);

        System.out.println(mustang);

        assertEquals(4, 2 + 2);

        if(rav4 instanceof SUV) {
            SUV rav4SUV = (SUV) rav4;
            rav4SUV.getHeight();
            ((SUV) rav4).getHeight();
        }

        Database database = Database.getDatabase();

    }
}