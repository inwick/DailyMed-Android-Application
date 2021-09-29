package com.united.dailymed;

import com.united.dailymed.AddDietInfo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class IT20274320_DietTesting {

    private static AddDietInfo NewMeal;
    private static String MealName;
    private static String MealOutput;

    @BeforeClass
    public static void initmain(){
        NewMeal = new AddDietInfo();
    }

    @Before
    public void setUp(){
        MealName="";

    }
    @Test
    public void TestMeal(){
       MealName="Rice";
        MealOutput = NewMeal.AddDiet(MealName);
        assertEquals("Fride rice", MealOutput,"Rice");
    }
    @After
    public void clear(){
        MealName="";

    }

    @AfterClass
    public static void clearAll(){
        NewMeal = null;
    }

}

