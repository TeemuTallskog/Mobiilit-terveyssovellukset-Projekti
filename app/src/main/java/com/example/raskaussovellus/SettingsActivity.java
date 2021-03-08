package com.example.raskaussovellus;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {


        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
            ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Settings");

    }

    /**
     * click button -> alarm class
     * @param view
     */

    public void launchActivity(View view){
        Intent intent = new Intent(this, Alarm.class);
        startActivity(intent);

    }

    public void wipeData(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to \n remove all previous logs?").setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CalendarDatabase db = new CalendarDatabase(SettingsActivity.this);
                db.wipeData();
                dialog.cancel();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

