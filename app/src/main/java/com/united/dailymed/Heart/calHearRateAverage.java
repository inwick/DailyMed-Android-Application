package com.united.dailymed.Heart;

public class calHearRateAverage {
    public float calAverage(int total, int noOfEntries) {
        float avg = (float) total / (float) noOfEntries;
        return avg;
    }
}
