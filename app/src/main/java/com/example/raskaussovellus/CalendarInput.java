package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class CalendarInput extends AppCompatActivity {
    private String dateID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_input);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        dateID = intent.getStringExtra(CalendarActivity.DATE_ID);
        String selectedDate = intent.getStringExtra(CalendarActivity.DATE);

        TextView displayDate = findViewById(R.id.dateView);
        displayDate.setText(selectedDate);
    }
}