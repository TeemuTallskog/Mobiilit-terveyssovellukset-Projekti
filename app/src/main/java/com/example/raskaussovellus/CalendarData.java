package com.example.raskaussovellus;

public class CalendarData {
    /**
     * CalendarData stores the data related to a certain date.
     */

    private String customData;
    //dateID will be year + month + day of the month  ex. 20201113
    private String dateID;
    private double weight;
    private int mood;

    public CalendarData(String dateID, String customData) {
        this.customData = customData;
        this.dateID = dateID;
        this.weight = 0;
    }

    public CalendarData(String dateID, String customData, double weight, int mood){
        this.dateID = dateID;
        this.customData = customData;
        this.weight = weight;
        this.mood = mood;
    }

    public CalendarData(String dateID, double weight){
        this.weight = weight;
        this.dateID = dateID;
        this.customData = "nothing here";
    }

    public CalendarData(){
        this.weight = 0;
        this.customData = "Custom log";
        this.mood = 0;
    }

    public CalendarData(String dateID){
        this.dateID = dateID;
    }

    public void setCustomData(String customData){
        this.customData = customData;
    }

    public void setDateID(String dateID){
        this.dateID = dateID;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public void setMood(int mood){
        this.mood = mood;
    }


    public double getWeight(){
        return weight;
    }


    public String getCustomData() {
        return customData;
    }

    public String getDateID() {
        return dateID;
    }

    public int getMood(){
        return mood;
    }
}
