package com.united.dailymed;

import com.united.dailymed.Heart.calHearRateAverage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class IT20205638_HeartReportTest {

    private static calHearRateAverage cal;
    private static int total;
    private static int noOfEntries;
    private static float output;

    @BeforeClass
    public static void initMain(){
        cal = new calHearRateAverage();
    }

    @Before
    public void setUp(){
        total = 0;
        noOfEntries = 0;
        output = 0.0f;
    }

    @Test
    public void calAverage1(){
        total = 200;
        noOfEntries = 4;
        output = cal.calAverage(total,noOfEntries);
        assertEquals(50,output,0.01);
    }

    @Test
    public void calAverage2(){
        total = 120;
        noOfEntries = 7;
        output = cal.calAverage(total,noOfEntries);
        assertEquals(17.14,output,0.01);
    }

    @After
    public void clear(){
        total = 0;
        noOfEntries = 0;
        output = 0.0f;
    }

    @AfterClass
    public static void clearAll(){
        cal = null;
    }

}