package com.example.raskaussovellus;

public class CalendarData {
    /**
     * CalendarData stores the data related to a certain date.
     */

    private String customData;
    //dateID will be the day of the month + month + year. ex. 13112020
    private int dateID;
    private float weight;
    private int mood;

    public CalendarData(int dateID, String customData) {
        this.customData = customData;
        this.dateID = dateID;
        this.weight = 0;
    }

    public CalendarData(int dateID, String customData, float weight){
        this.dateID = dateID;
        this.customData = customData;
        this.weight = weight;
    }

    public CalendarData(int dateID, float weight){
        this.weight = weight;
        this.dateID = dateID;
        this.customData = "nothing here";
    }

    public CalendarData(){
        this.customData = "nothing here";
        this.weight = 0;
    }

    public float getWeight(){
        return weight;
    }


    public String getCustomData() {
        return customData;
    }

    public int getDateID() {
        return dateID;
    }
}
