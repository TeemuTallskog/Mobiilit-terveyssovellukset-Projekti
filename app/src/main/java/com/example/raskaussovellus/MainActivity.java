package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView drawer;

    private long dif;
    private long daysCount;

    private TextView showDate, counter, txtTimerDay1;
    private Button btnChangeDate;

    private int day;
    private int month;
    private int year;

    static final int DATE_DIALOG_ID = 999;

    CountDownTimer countDownTimer;
    long timeStart = DateUtils.DAY_IN_MILLIS * 281 +
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
        counter = (TextView) findViewById(R.id.days);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }


    public void btnOnClickListener() {
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                //date pick limiter
                //datePickerListener.getDatePicker().setMaxDate(System.currentTimeMillis());
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

            String chosenDate = day + "." + (month + 1) + "." + year;
            showDate.setText("chosen date: " + chosenDate + "\n"
                    + "estimated birth: " + dateAdding(280, chosenDate));

            calcDif();
            startTimer();
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
        countDownTimer = new CountDownTimer(timeStart, 1000) {
            StringBuilder time = new StringBuilder();

            @Override
            public void onTick(long millisUntilFinished) {
                time.setLength(0);
                // singular or plural day
                if (millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
                    long count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
                    count = count - daysCount;
                    if (count > 1)
                        time.append(count).append(" days left ");
                    else
                        time.append(count).append(" day left ");

                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
                    Log.d("TAG", "test");

                    //display how many days from picked to current date (for testing)
                    //txtTimerDay1 = (TextView) findViewById(R.id.txtTimerDayss);
                    //txtTimerDay1.setText("" + String.format("%02d", daysCount));
                }
                //hours, mins, secs (for testing)
                time.append(DateUtils.formatElapsedTime(Math.round(millisUntilFinished / 1000d)));
                //days
                counter.setText(time.toString());
            }
            @Override
            public void onFinish() {
                counter.setText(DateUtils.formatElapsedTime(0));
                Log.d("TAG", "finished");
            }
        }.start();
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


    //shared prefs
   /* @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", timeStart);

        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        timeStart = prefs.getLong("millisLeft", DATE_DIALOG_ID);
        startTimer();

    }*/

}