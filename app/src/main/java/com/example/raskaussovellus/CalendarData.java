package com.example.raskaussovellus;

public class CalendarData {

    private String customData;
    private int dateID;

    public CalendarData(int dateID, String customData) {
        this.customData = customData;
        this.dateID = dateID;
    }

    public CalendarData(){
        this.customData = "nothing here";
    }

    public String getCustomData() {
        return customData;
    }

    public int getDateID() {
        return dateID;
    }
}
