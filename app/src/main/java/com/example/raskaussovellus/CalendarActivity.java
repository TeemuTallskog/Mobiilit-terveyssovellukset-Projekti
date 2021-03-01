package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormatSymbols;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarView";
    public static final String DATE = "Date";
    public static final String DATE_ID = "DateID";

    private String dateID;
    private String selectedDate;
    private TextView customDataBox;
    private EditText textInput;
    private CalendarDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        CalendarView calendarView = findViewById(R.id.calendarView);
        customDataBox = findViewById(R.id.customData);
        //adds a back button to the toolbar that leads to the MainActivity
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        String currentDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String currentMonth = Integer.toString(calendar.get(Calendar.MONTH));
        String currentYear = Integer.toString(calendar.get(Calendar.YEAR));
        selectedDate = new DateFormatSymbols().getMonths()[Integer.parseInt(currentMonth)] + " " + currentDay + ", " + currentYear;

        db = new CalendarDatabase(this);

        textInput = findViewById(R.id.textInput);

        //Updates the textview for that day automatically from database
        CalendarData currentDayData = db.getCalendarData(currentDay + currentMonth + currentYear);
        customDataBox.setText(currentDayData.getCustomData());


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             * Adds a listener that checks when the user clicks on a certain date and displays the data related to that date.
             */
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateID = String.valueOf(dayOfMonth) + String.valueOf(month) + String.valueOf(year);
                selectedDate = new DateFormatSymbols().getMonths()[month] + " " + String.valueOf(dayOfMonth) + ", " + String.valueOf(year);
                CalendarData calendarData = db.getCalendarData(dateID);
                customDataBox.setText(calendarData.getCustomData());
            }
        });
    }


    public void onBtnSubmit(View v){
        //submits the users text to the database and updates the text box
        String text = textInput.getText().toString();
        CalendarData calendarData = new CalendarData(Integer.parseInt(dateID), text);
        db.addData(calendarData);
        customDataBox.setText(text);

        //hides the keypad once submit button is pressed.
        textInput.setText("");
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(textInput.getWindowToken(), 0);
    }

    public void onBtnRemove(View v){
        db.deleteData(dateID);
        customDataBox.setText("Nothing here");
    }

    public void newEntry(View view){
        Intent intent = new Intent(this, CalendarInput.class);
        intent.putExtra(DATE, selectedDate);
        intent.putExtra(DATE_ID, dateID);
        startActivity(intent);
    }
}