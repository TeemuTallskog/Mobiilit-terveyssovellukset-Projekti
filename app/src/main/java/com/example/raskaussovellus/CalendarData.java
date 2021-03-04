package com.example.raskaussovellus;

public class CalendarData {
    /**
     * CalendarData stores the data related to a certain date.
     */

    private String customData;
    //dateID will be the day of the month + month + year. ex. 13112020
    private int dateID;
    private double weight;
    private int mood;

    public CalendarData(int dateID, String customData) {
        this.customData = customData;
        this.dateID = dateID;
        this.weight = 0;
    }

    public CalendarData(int dateID, String customData, double weight, int mood){
        this.dateID = dateID;
        this.customData = customData;
        this.weight = weight;
        this.mood = mood;
    }

    public CalendarData(int dateID, double weight){
        this.weight = weight;
        this.dateID = dateID;
        this.customData = "nothing here";
    }

    public CalendarData(){
        this.weight = 0;
        this.customData = "Custom log";
        this.mood = 0;
    }

    public CalendarData(int dateID){
        this.dateID = dateID;
    }

    public void setCustomData(String customData){
        this.customData = customData;
    }

    public void setDateID(int dateID){
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

    public int getDateID() {
        return dateID;
    }

    public int getMood(){
        return mood;
    }
}
