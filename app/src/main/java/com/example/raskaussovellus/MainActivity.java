package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView drawer;

    private boolean timerIsRunning = false;
    //
    private long mEndTime;
    private boolean mTimerRunning;
    private long startTimeInMillis;
    private long timeStartTest;
    private long mEndTimeCount;

    private long dif;
    private long daysCount;
    private long count;
    private long weeks;

    private TextView showDate, daysLeft, weeksLeft;
    private Button btnChangeDate, btnSave;

    private TextView daysLeftG;
    private String chosenDate, stringDate;

    private int day;
    private int month;
    private int year;


    static final int DATE_DIALOG_ID = 999;
    public static final String SHARED_PREFS = "sharedPrefs";



    CountDownTimer countDownTimer;
    long timeStart = DateUtils.DAY_IN_MILLIS * 281+
            DateUtils.HOUR_IN_MILLIS * 0 +
            DateUtils.MINUTE_IN_MILLIS * 0 +
            DateUtils.SECOND_IN_MILLIS * 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this activity will handle click events.
        drawer = (NavigationView) findViewById(R.id.navigationView);
        drawer.setNavigationItemSelectedListener(this);

        btnSave = (Button) findViewById(R.id.saveButton);
        viewDate();
        daysLeftG = (TextView) findViewById(R.id.textView2);
        btnOnClickListener();
        btnSave();

        /**
         * if id (imageMenu) clicked; drawerLayout will open.
         */
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawerLayout.openDrawer(GravityCompat.START);
                Log.d("TAG", "nav clicked");
            }
        });
    }

    public void launchActivity(View view){
        Intent intent = new Intent(this, Alarm.class);
        startActivity(intent);

    }

    /**
     * @param item is the unique item in the drawer
     * @return true when item matches object id
     * if the item id corresponds to the clicked item in the drawer,
     * a specific activity will be started.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();

        if (id == R.id.nav_calendar){
            Log.d("TAG", "calendar clicked");
            intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.nav_info) {
            Log.d("TAG", "info clicked");
            intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.nav_settings) {
            Log.d("TAG", "settings clicked");
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


    // display current date, after added 280 days, and counter
    public void viewDate() {
        showDate = (TextView) findViewById(R.id.date);
        daysLeft = (TextView) findViewById(R.id.days);
        weeksLeft = (TextView) findViewById(R.id.weeks);

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    //save and start countdown button (for testing)
    public void btnSave() {
        btnSave = (Button) findViewById(R.id.saveButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(timerIsRunning == true){
                    countDownTimer.cancel();
                    timerIsRunning = false;
                }
                if(!timerIsRunning){
                startTimer();
                }*/
            }
        });
    }

    public void btnOnClickListener() {
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set current date and limit future date
            DatePickerDialog dialog = new  DatePickerDialog(this, datePickerListener, year, month, day);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            return dialog;
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        //setting the date
        //assigning year,month,day variable to the chosen year,month,day
        //displays the chosen date and after the added 280 days as strings
        //calling methods calcDif and startTimer when datepicker is closed to -
        //display the appropriate amount of days left and start the timer
        @SuppressLint("SetTextI18n")
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            chosenDate = day + "." + (month + 1) + "." + year;
            stringDate = ("chosen date: " + chosenDate + "\n"
                    + "estimated birth: " +  dateAdding(280, chosenDate));
            showDate.setText(stringDate);

            if(timerIsRunning){
                countDownTimer.cancel();
                timerIsRunning = false;
            }
            startTimer();
            saveData();
            calcDif();
        }
    };

    //adds 280 days to the picked date
    String dateAdding(int AddedDays, String Date) {
        int days = AddedDays;
        String afterAdding = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            java.util.Date date = null;
            String string = Date;
            date = dateFormat.parse(string);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            afterAdding = dateFormat.format(calendar.getTime());

        } catch (Exception error) {
            error.printStackTrace();
        }
        return afterAdding;
    }

    // reduce count(280) from the daysCount variable and countdown
    private void startTimer() {
        timerIsRunning = true;
        //
        mEndTime = System.currentTimeMillis() + timeStart;
        //mEndTimeCount = System.currentTimeMillis() + count;

        countDownTimer = new CountDownTimer(timeStart, 1000) {
            StringBuilder timeDay = new StringBuilder();
            StringBuilder timeWeek = new StringBuilder();

            @Override
            public void onTick(long millisUntilFinished) {
                //
                //timeStartTest = millisUntilFinished;

                timeDay.setLength(0);
                timeWeek.setLength(0);
                // singular or plural day & week

                if (millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
                    count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
                    count = count - daysCount;
                    weeks = count / 7;
                    if (count > 1){
                        timeDay.append(count).append(" days left ");
                    }
                    else{
                        timeDay.append(count).append(" day left ");
                    }
                    if(weeks > 1){
                        timeWeek.append(weeks).append(" weeks left ");
                    }
                    else{
                        timeWeek.append(weeks).append(" week left ");
                    }

                    //timer format (hours, mins, secs) for testing
                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
                    Log.d("TAG", "test");
                }

                //hours, mins, secs (for testing)
                timeDay.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));

                //days, weeks to string
                daysLeft.setText(timeDay.toString());
                weeksLeft.setText(timeWeek.toString());
            }
            @Override
            public void onFinish() {
                daysLeft.setText(DateUtils.formatElapsedTime(0));
                daysLeft.setText("Awaiting birth");
                Log.d("TAG", "finished");
                mTimerRunning = false;
            }
        }.start();
        //
        mTimerRunning = true;
    }

    //reduce the picked date to current date from 280 days and assign it to the daysCount variable
    public void calcDif() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String chosenDate = day + "-" + (month + 1) + "-" + year;
            Date chosen = dateFormat.parse(chosenDate);
            Date currentDate = new Date();
            dif = currentDate.getTime() - chosen.getTime();
            daysCount = dif / (24 * 60 * 60 * 1000);

        } catch (Exception error) {
            error.printStackTrace();
        }
    }


    //SharedPreferences for timer
    public void saveDateTimer(){
        SharedPreferences prefGet = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefGet.edit();

        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.putLong("millisLeft", timeStart);
        editor.putLong("startTimeInMillis", startTimeInMillis);

        //count
        //editor.putLong("days", count);
        //editor.putLong("startTimeInDays", timeStartTest);

        editor.apply();
    }

    public void loadDataTimer() {
        SharedPreferences prefGet = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);

        startTimeInMillis = prefGet.getLong("startTimeInMillis", timeStart);
        timeStart = prefGet.getLong("millisLeft", startTimeInMillis);

        //count
        //timeStartTest= prefGet.getLong("startTimeInDays", count);
        //count = prefGet.getLong("days", timeStartTest);


        mTimerRunning = prefGet.getBoolean("timerRunning", false);
        if (mTimerRunning) {
            mEndTime = prefGet.getLong("endTime", 0);
            timeStart = mEndTime - System.currentTimeMillis();
            //count = mEndTime -System.currentTimeMillis();
            if (timeStart < 0) {
                timeStart = 0;
                mTimerRunning = false;
            } else {
                startTimer();
            }
        }
    }



    //SharedPreferences for date
    public void saveData(){
        SharedPreferences prefGet = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefGet.edit();
        editor.putString("date", stringDate);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences prefGet = getSharedPreferences(SHARED_PREFS, Activity.MODE_PRIVATE);
        String dateValue = prefGet.getString("date", "Date not selected");
        showDate.setText(dateValue);
    }

    // date's loadData method onStart
    @Override
    protected void onStart() {
        super.onStart();
        loadData();
        loadDataTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveDateTimer();
    }
}