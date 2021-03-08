package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView drawer;
    private Button btnChangeDate;
    private int day;
    private int month;
    private int year;
    static final int DATE_DIALOG_ID = 999;
    public static final String DATE = "date";
    public static final String DATE_ID = "dateID";
    public static final String PREFS = "com.raskaussovellus.app.expected_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this activity will handle click events.
        drawer = (NavigationView) findViewById(R.id.navigationView);
        drawer.setNavigationItemSelectedListener(this);
        viewDate();
        btnOnClickListener();

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
        pullPrefs();
    }

    @Override
    public void onResume(){
        super.onResume();
        pullPrefs();
    }

    @Override
    public void onStart(){
        super.onStart();
        pullPrefs();
    }

    /**
     * pulls the set date from preferences and sets values accordingly.
     */
    private void pullPrefs(){
        TextView weekLeftTV = findViewById(R.id.weeks);
        TextView daysLeftTV = findViewById(R.id.days);
        TextView date = findViewById(R.id.date);
        SharedPreferences preferences = getSharedPreferences(PREFS, MainActivity.MODE_PRIVATE);
        ExpectedDateHandler dateHandler = new ExpectedDateHandler();
        long pickedDate = preferences.getLong(PREFS, 0);
        daysLeftTV.setText(dateHandler.daysLeft(pickedDate));
        weekLeftTV.setText(dateHandler.weeksLeft(pickedDate) + " Weeks left");
        date.setText(dateHandler.getExpectedDate(pickedDate));
        updateProgressBar(dateHandler.getProgress(pickedDate));
        getFunFact(dateHandler.weeksLeft(pickedDate));
    }


    /**
     * updates the progress bar
     * @param value inputvalue is the precentage.
     */
    private void updateProgressBar(int value){
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Progress_bar);
        Log.i("tag", String.valueOf(value));
        progressBar.setProgress(value);
        TextView textView = findViewById(R.id.precentageText);
        textView.setText(String.valueOf(value) + "%");
    }

    /**
     * retrieves a string from FunFacts.java and sets it on a textview
     * @param week param is weeks left
     */
    private void getFunFact(int week){
        week = 40 - week;
        TextView textView = findViewById(R.id.funFact);
        FunFacts funFacts = new FunFacts();
        textView.setText(funFacts.getFact(week));
    }

    /**
     * launches CalendarInput activity
     * @param view
     */
    public void launchActivity(View view){
        final Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        String date = new DateFormatSymbols().getMonths()[currentMonth] + " " + currentDay + ", " + currentYear;
        String dateID = Integer.toString(currentYear) + underTen(currentMonth + 1) + underTen(currentDay);
        Intent intent = new Intent(this, CalendarInput.class);
        intent.putExtra("Origin", "Main");
        intent.putExtra(DATE, date);
        intent.putExtra(DATE_ID, dateID);
        startActivity(intent);

    }

    /**
     * if value is under 10. it adds a 0 in front of the number.
     * @param i
     * @return returns a string containing int
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
        if(id == R.id.nav_data){
            intent = new Intent(this, DataAnalysis.class);
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
        if(id == R.id.inputHistory){
            intent = new Intent(this, LogHistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


    /**
     * pulls the current date.
     */
    public void viewDate() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
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

        /**
         * gets the date picked from the date picker and saves that date to SharedPreferences as milliseconds.
         * @param view
         * @param selectedYear
         * @param selectedMonth
         * @param selectedDay
         */
        @SuppressLint("SetTextI18n")
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth+1;
            day = selectedDay;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            long dateInMillis = 0;
            try {
                Date date = sdf.parse(year + "/" + underTen(month) + "/" + underTen(day));
                Log.i("tag", date.toString());
                dateInMillis = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i("tag, second", String.valueOf(dateInMillis));
            SharedPreferences preferences = getSharedPreferences(PREFS, MainActivity.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = preferences.edit();
            prefEditor.putLong(PREFS, dateInMillis);
            prefEditor.commit();
            ExpectedDateHandler dateHandler = new ExpectedDateHandler();

            pullPrefs();
        }
    };
}