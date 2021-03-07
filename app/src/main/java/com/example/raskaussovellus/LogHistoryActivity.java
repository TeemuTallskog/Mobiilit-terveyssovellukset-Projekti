package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class LogHistoryActivity extends AppCompatActivity {

    public final static String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_history);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CalendarDatabase database = new CalendarDatabase(this);

        ListView listView = findViewById(R.id.LogHistoryList);
        ArrayList<CalendarData> calendarData = database.getAllData();

        LogHistoryAdapter adapter = new LogHistoryAdapter(this, calendarData);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LogHistoryActivity.this, LogInspect.class);
                intent.putExtra(POSITION, position);
                startActivity(intent);
            }
        });
    }
}