package com.example.raskaussovellus;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {


        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    public void launchActivity(View view){
        Intent intent = new Intent(this, Alarm.class);
        startActivity(intent);

    }

    public void wipeData(View view){
            CalendarDatabase db = new CalendarDatabase(this);
            db.wipeData();
    }

    }

