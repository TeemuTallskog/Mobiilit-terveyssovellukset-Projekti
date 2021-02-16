package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarView";

    private String dateID;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new CalendarDatabase(this);

        textInput = findViewById(R.id.textInput);

        //adds a listener that knows when you click on a new date in the calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateID = String.valueOf(dayOfMonth) + String.valueOf(month) + String.valueOf(year);
                CalendarData calendarData = db.getCalendarData(dateID);
                customDataBox.setText(calendarData.getCustomData());
            }
        });
    }

    public void onBtnSubmit(View v){
        String text = textInput.getText().toString();
        CalendarData calendarData = new CalendarData(Integer.parseInt(dateID), text);
        db.addData(calendarData);
        customDataBox.setText(text);
        textInput.setText("");
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(textInput.getWindowToken(), 0);
    }

    public void onBtnRemove(View v){
        db.deleteData(dateID);
        customDataBox.setText("Nothing here");
    }
}