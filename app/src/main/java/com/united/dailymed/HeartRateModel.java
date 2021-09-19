package com.united.dailymed;

public class HeartRateModel{

    // variables for our coursename,
    // description, tracks and duration, id.

    private int id;
    private String heartRate;
    private String date;



    // creating getter and setter methods
    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getDate() {
        return date;
    }

    public void setCourseDuration(String date) {
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public HeartRateModel(int id, String heartRate, String date) {
        this.id = id;
        this.heartRate = heartRate;
        this.date = date;
    }
}
