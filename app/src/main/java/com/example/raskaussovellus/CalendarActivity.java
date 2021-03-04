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
import android.widget.ImageView;
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
    //private EditText textInput;
    private CalendarDatabase db;
    private TextView weightView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        CalendarView calendarView = findViewById(R.id.calendarView);
        customDataBox = findViewById(R.id.customData);
        weightView = findViewById(R.id.drawWeight);
        imageView = findViewById(R.id.drawMood);
        //adds a back button to the toolbar that leads to the MainActivity
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Calendar calendar = Calendar.getInstance();
        String currentDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String currentMonth = Integer.toString(calendar.get(Calendar.MONTH));
        String currentYear = Integer.toString(calendar.get(Calendar.YEAR));
        selectedDate = new DateFormatSymbols().getMonths()[Integer.parseInt(currentMonth)] + " " + currentDay + ", " + currentYear;
        dateID = currentDay + currentMonth + currentYear;

        db = new CalendarDatabase(this);

        //textInput = findViewById(R.id.textInput);

        //Updates the displayed data for current automatically from database
        CalendarData currentDayData = db.getCalendarData(currentDay + currentMonth + currentYear);
        if(currentDayData.getCustomData().equals("Custom log")){
            customDataBox.setText("");
        }else{
            customDataBox.setText(currentDayData.getCustomData());
        }
        setMoodImage(currentDayData.getMood());
        weightView.setText(String.format(getResources().getString(R.string.DrawWeight),String.valueOf(currentDayData.getWeight())));


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             * Adds a listener that checks when the user clicks on a certain date and displays the data related to that date.
             */
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateID = String.valueOf(dayOfMonth) + String.valueOf(month) + String.valueOf(year);
                selectedDate = new DateFormatSymbols().getMonths()[month] + " " + String.valueOf(dayOfMonth) + ", " + String.valueOf(year);
                CalendarData calendarData = db.getCalendarData(dateID);
                if(calendarData.getCustomData().equals("Custom log")){
                    customDataBox.setText("");
                }else{
                    customDataBox.setText(calendarData.getCustomData());
                }
                weightView.setText(String.format(getResources().getString(R.string.DrawWeight),String.valueOf(calendarData.getWeight())));
                setMoodImage(calendarData.getMood());

            }
        });
    }

    /**
     *  changes the mood image according to the parameter
     * @param mood as a parameter you pull the mood data for that day from database
     */
    public void setMoodImage(int mood){
        if(mood == 0){
            imageView.setImageResource(R.drawable.empty_mood_input);
        }else if(mood == 1){
            imageView.setImageResource(R.drawable.emoji_1_48);
        }else if(mood == 2){
            imageView.setImageResource(R.drawable.emoji_2_48);
        }else if(mood == 3){
            imageView.setImageResource(R.drawable.emoji_3_48);
        }else if(mood == 4){
            imageView.setImageResource(R.drawable.emoji_4_48);
        }
    }

    /*public void onBtnSubmit(View v){
        //submits the users text to the database and updates the text box
        String text = textInput.getText().toString();
        CalendarData calendarData = new CalendarData(Integer.parseInt(dateID), text);
        db.addData(calendarData);
        customDataBox.setText(text);

        //hides the keypad once submit button is pressed.
        textInput.setText("");
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(textInput.getWindowToken(), 0);
    }*/

    public void onBtnRemove(View v){
        db.deleteData(dateID);
        customDataBox.setText("");
        setMoodImage(0);
        weightView.setText("0.0 Kg");
    }

    public void newEntry(View view){
        Intent intent = new Intent(this, CalendarInput.class);
        intent.putExtra(DATE, selectedDate);
        intent.putExtra(DATE_ID, dateID);
        startActivity(intent);
    }
}