package com.example.raskaussovellus;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "calendarData";
    private static final String TABLE_NAME = "calendar";
    private static final String CUSTOM_DATA = "userData";
    private static final String KEY_ID = "id";
    private static final String WEIGHT = "kg";
    private static final String MOOD = "mood";


    public CalendarDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate makes a table for the database to store data
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CALENDAR_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " TEXT PRIMARY KEY, " + CUSTOM_DATA + " TEXT, " + WEIGHT + " DOUBLE, " + MOOD + " INTEGER" + ")";
        db.execSQL(CREATE_CALENDAR_TABLE);
    }

    //When updating the app it removes the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    /**
     * addData can be called with a CaledarData object as a parameter to store the data inside it to the database.
     * @param data
     */
    public void addData(CalendarData data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CUSTOM_DATA, data.getCustomData());
        values.put(KEY_ID, data.getDateID());
        values.put(WEIGHT, data.getWeight());
        values.put(MOOD, data.getMood());
        db.insertWithOnConflict(TABLE_NAME, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    /**
     * You can call deleteData with a date as a parameter to remove the data related to that date.
     * @param dateID
     */
    public void deleteData(String dateID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{dateID});
        db.close();
    }

    /**
     * getCalendarData can be called with a date as a parameter that returns data tied to that date.
     * @param id
     * @return
     */
    CalendarData getCalendarData(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, CUSTOM_DATA, WEIGHT, MOOD}, KEY_ID + "=?", new String[]{id},null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        if(cursor.getCount() != 0){
            return new CalendarData(cursor.getString(0), cursor.getString(1), Double.parseDouble(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
        }else {
            return new CalendarData();
        }
    }

    /**
     * Method to get weight data from the data base.
     * Weight inputs lower than 1.0 kg will be ignored.
     * @return returns datapoints with the days months and years in milliseconds for the x value and weight as the Y value from the database.
     * @throws Exception
     */
   public DataPoint [] getWeight() throws Exception {
       SQLiteDatabase db = this.getReadableDatabase();

       @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT " + KEY_ID + ", " + WEIGHT + " FROM " + TABLE_NAME + " ORDER BY " + KEY_ID + " ASC", null);
       if(cursor != null) {
           cursor.moveToFirst();
       }
       //gets the amount of valid weight data from database. if weight is below 1kg it will be ignored
       int validWeightCount = 0;
       for(int i = 0; i<cursor.getCount(); i++){
           if(cursor.getDouble(1)> 1.0){
                 validWeightCount= validWeightCount + 1;
           }
           cursor.moveToNext();
       }

       cursor.moveToFirst();
       int dataIndex = 0;
       DataPoint[] dataPoints = new DataPoint[validWeightCount];
       for (int i = 0; i < cursor.getCount(); i++) {
           if(cursor.getDouble(1) < 1.0){
               cursor.moveToNext();
           }else {
               dataPoints[dataIndex] = new DataPoint(formatDate(cursor.getString(0)), cursor.getDouble(1));
               dataIndex = dataIndex + 1;
               cursor.moveToNext();
           }
       }
       return dataPoints;
   }

    /**
     * retrieves mood datapoints from the database
     * @return returns datapoints for the GraphView
     * @throws Exception
     */
   public DataPoint [] getMood() throws Exception{
       SQLiteDatabase db = this.getReadableDatabase();
       @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT " + KEY_ID + ", " + MOOD + " FROM " + TABLE_NAME + " ORDER BY " + KEY_ID + " ASC", null);
       if(cursor != null) {
           cursor.moveToFirst();
       }

       DataPoint[] dataPoints = new DataPoint[cursor.getCount()];
       for (int i = 0; i < cursor.getCount(); i++) {
           dataPoints[i] = new DataPoint(formatDate(cursor.getString(0)), cursor.getInt(1));
           cursor.moveToNext();
       }
       return dataPoints;
   }

    /**
     * Pulls all data from the database and returns in in arraylist filled with CalendarData objects.
     * @return ArrayList<CalendarData>. CalendarData contains DateID, CustomText, Weight, Mood.
     *
     */
   public ArrayList<CalendarData> getAllData(){
       SQLiteDatabase db = this.getReadableDatabase();
       ArrayList<CalendarData> calendarDataArrayList = new ArrayList<>();
       @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_ID + " DESC", null);
       if(cursor != null){
           cursor.moveToFirst();
       }
       for(int i = 0;i<cursor.getCount(); i++){
            calendarDataArrayList.add(new CalendarData(cursor.getString(0), cursor.getString(1), cursor.getDouble(2), cursor.getInt(3)));
            cursor.moveToNext();
       }
       return calendarDataArrayList;
   }


    /**
     * Turns dateID stored in the database into milliseconds.
     * @param dateID parameter is a date in yyyyMMdd format
     * @return returns the date in milliseconds.
     */
    private long formatDate(String dateID)throws Exception{
        @SuppressLint("SimpleDateFormat") Date date =new SimpleDateFormat("yyyyMMdd").parse(dateID);
        return date.getTime();
    }

    /**
     * Call this to remove all records from the database table
     */
    public void wipeData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }
}
