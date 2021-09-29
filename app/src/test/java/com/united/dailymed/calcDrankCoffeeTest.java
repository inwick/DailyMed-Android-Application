package com.united.dailymed;

import org.junit.Test;

import static org.junit.Assert.*;

public class calcDrankCoffeeTest {
    private calcDrankCoffee cal;
    private static double drankAlready;
    private static double amountNow;
    private static double remaining;
    private static double amt;
    private calcDrankCoffee calRem;

    @Test
    public void calcDrank() {
        cal=new calcDrankCoffee();
        Double results = cal.calcDrank(1000.00,500.00);
       assertEquals(1500.00,results,0.001);

    }

    @Test
    public void calcRem() {
        calRem=new calcDrankCoffee();
        Double results = calRem.calRemaining(1350.00,80.00);
        assertEquals(1270.00,results,0.001);

    }


}
