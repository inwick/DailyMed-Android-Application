package com.united.dailymed.Model;

public class PillModel {
    private int id, status;
    private String pill;
    private String pillTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPill() {
        return pill;
    }

    public void setPill(String pill) {
        this.pill = pill;
    }

    public String getPillTime() {
        return pillTime;
    }

    public void setPillTime(String pillTime) {
        this.pillTime = pillTime;
    }
}
