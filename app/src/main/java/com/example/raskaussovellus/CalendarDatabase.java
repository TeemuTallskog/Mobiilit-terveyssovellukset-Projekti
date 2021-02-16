package com.example.raskaussovellus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CalendarDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "calendarData";
    private static final String TABLE_NAME = "calendar";
    private static final String CUSTOM_DATA = "userData";
    private static final String KEY_ID = "id";


    public CalendarDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creates the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CALENDAR_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + CUSTOM_DATA + " TEXT" + ")";
        db.execSQL(CREATE_CALENDAR_TABLE);
    }

    //When updating the app it removes the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    //You can call this to add the CalendarData object values to the database
    public void addData(CalendarData data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CUSTOM_DATA, data.getCustomData());
        values.put(KEY_ID, data.getDateID());
        db.insertWithOnConflict(TABLE_NAME, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    //You can call this to remove a specified calendar data
    public void deleteData(String dateID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{dateID});
        db.close();
    }

    //you can call this to get the custom data from a certain date from the database
    CalendarData getCalendarData(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, CUSTOM_DATA}, KEY_ID + "=?", new String[]{id},null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        if(cursor.getCount() != 0){
            return new CalendarData(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }else {
            return new CalendarData();
        }
    }
}
