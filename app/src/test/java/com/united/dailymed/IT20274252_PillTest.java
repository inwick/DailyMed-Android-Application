package com.united.dailymed;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IT20274252_PillTest {
    private static addPillDetails newPill;
    private static String pillName;
    private static String outPill;
    private static int amount;
    private static int outAmt;

    @BeforeClass
    public static void initmain() {
        newPill = new addPillDetails();
    }

    @Before
    public void setUp() {
        pillName = "";
        amount = 0;
    }

    @Test
    public void TestTxt() {
        pillName = "Panadol";
        outPill = newPill.AddPills(pillName);
        assertEquals("paracetamol", outPill, "Panadol");
    }

    @Test
    public void TestAmt() {
        amount = 5;
        outAmt = newPill.AddAmount(amount);
        assertEquals(5, outAmt, 0.01);
    }

    @After
    public void clear() {
        pillName = "";
        amount = 0;
    }

    @AfterClass
    public static void clearAll() {
        newPill = null;
    }

}
