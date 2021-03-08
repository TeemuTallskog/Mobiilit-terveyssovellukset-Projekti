package com.example.raskaussovellus;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Handles the expected date calculations from the set date and returns readable data.
 */
public class ExpectedDateHandler {
    long nineMonthsMillis  = TimeUnit.DAYS.toMillis(280);

    public ExpectedDateHandler(){

    }

    /**
     * Calculates the days left until 280 days have gone from the inputted data.
     * @param pickedDate picked date in milliseconds.
     * @return
     */
    public String daysLeft(long pickedDate){
        long daysLeftMillis = getDaysLeftInMillis(pickedDate);
        int daysLeft;
        if(daysLeftMillis > 0) {
            daysLeft = (int) TimeUnit.MILLISECONDS.toDays(daysLeftMillis);
        }else{
            daysLeft = 0;
        }
        return daysLeft + " Days left";
    }

    /**
     * calculates the weeks left until 280 days have gone from the inputted date.
     * @param pickedDate is the picked date in milliseconds
     * @return  returns a String
     */
    public int weeksLeft(long pickedDate){
        long daysLeftMillis = getDaysLeftInMillis(pickedDate);
        if(daysLeftMillis > 0) {
            int daysLeft = (int) TimeUnit.MILLISECONDS.toDays(daysLeftMillis);
            int weeksLeft = daysLeft / 7;
            return weeksLeft;
        }else{
            return 0;
        }

    }

    /**
     * gets the date 280 days from the picked date
     * @param pickedDate is a date in milliseconds
     * @return returns the expected date.
     */
    public String getExpectedDate(long pickedDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(pickedDate + nineMonthsMillis);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        if(year == 1970){
            return " ";
        }else {
            return "Expected birthdate: " + day + "." + month + "." + year;
        }
    }

    /**
     * calculates milliseconds left until 280days have gone from he date inputted.
     * @param pickedDate is a date in milliseconds.
     * @return returns days left in milliseconds.
     */
    private long getDaysLeftInMillis(long pickedDate){
        Date date = new Date();
        long currentDay = date.getTime();
        long expectedDate = pickedDate + nineMonthsMillis;
        long daysLeftMillis = expectedDate - currentDay;
        return daysLeftMillis;
    }

    /**
     * the precentage of how far current date is from the expected date.
     * @param pickedDate is a date in milliseconds.
     * @return
     */
    public int getProgress(long pickedDate){
        long daysLeftInMillis =  getDaysLeftInMillis(pickedDate);
        double precentageDouble = ((double)daysLeftInMillis / nineMonthsMillis) * 100;
        int precentage = 100 - (int)precentageDouble;
        Log.i("tag", String.valueOf(precentage));
        if((int)precentage<100) {
            return (int) precentage;
        }else{
            return 100;
        }
    }
}
