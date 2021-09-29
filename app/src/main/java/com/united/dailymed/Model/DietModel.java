package com.united.dailymed.Model;


public class DietModel{

    // variables for our coursename,
    // description, tracks and duration, id.

    private int id;
    private String date;
    private String bfirst;
    private String lunch;
    private String dinner;


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getBfirst() {
        return bfirst;
    }

    public String getLunch() {
        return lunch;
    }

    public String getDinner() {
        return dinner;
    }

    // constructor
    public DietModel(int id, String date, String bfirst,String lunch,String dinner) {
        this.id = id;
        this.date = date;
        this.bfirst = bfirst;
        this.lunch = lunch;
        this.dinner = dinner;
    }
}
