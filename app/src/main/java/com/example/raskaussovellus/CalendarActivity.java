package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DateFormatSymbols;

import java.util.Calendar;
import java.util.Objects;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarView";
    public static final String DATE = "Date";
    public static final String DATE_ID = "DateID";

    private String dateID;
    private String selectedDate;
    private TextView customDataBox;
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
        imageView = findViewById(R.id.emojiLog);
        //adds a back button to the toolbar that leads to the MainActivity
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Calendar");
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        selectedDate = new DateFormatSymbols().getMonths()[currentMonth] + " " + currentDay + ", " + currentYear;
        dateID = Integer.toString(currentYear) + underTen(currentMonth + 1) + underTen(currentDay);

        db = new CalendarDatabase(this);

        //Updates the displayed data for current automatically from database
        CalendarData currentDayData = db.getCalendarData(dateID);
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
                dateID = year + underTen(month + 1) + underTen(dayOfMonth);
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
        }else if(mood == 4){
            imageView.setImageResource(R.drawable.emoji_1_48);
        }else if(mood == 3){
            imageView.setImageResource(R.drawable.emoji_2_48);
        }else if(mood == 2){
            imageView.setImageResource(R.drawable.emoji_3_48);
        }else if(mood == 1){
            imageView.setImageResource(R.drawable.emoji_4_48);
        }
    }

    /**
     * Asks confirmation on if you actually want to remove a certain data log.
     * if user agrees it removes a datalog based on the date selected.
     * @param v
     */
    public void onBtnRemove(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to \n remove this log?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteData(dateID);
                        customDataBox.setText("");
                        setMoodImage(0);
                        weightView.setText("0.0 Kg");
                        dialog.cancel();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void newEntry(View view){
        Intent intent = new Intent(this, CalendarInput.class);
        intent.putExtra("Origin", "Calendar");
        intent.putExtra(DATE, selectedDate);
        intent.putExtra(DATE_ID, dateID);
        startActivity(intent);
    }

    /**
     * if a number is under
     * @param i
     * @return
     */
    private String underTen(int i){
        String stringNum;
        if (i < 10){
            stringNum = "0" + i;
            return stringNum;
        }else{
            return Integer.toString(i);
        }
    }
}