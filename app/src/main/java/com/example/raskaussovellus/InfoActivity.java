package com.example.raskaussovellus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Useful links");

        // Find the list view from layout
        ListView lv = findViewById(R.id.informationList);

        // adapter uses to get the actual data to the view.
        lv.setAdapter(new ArrayAdapter<Information>(
                this,
                R.layout.link,
                Link.getInstance().getLink())

        );

        // waiting when button is clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //gets the index of clicked item
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Uri.parse -> Creates a Uri which parses the given encoded
                // ACTION_VIEW -> Display the data to the user
                Intent nextActivity = new Intent(Intent.ACTION_VIEW, Uri.parse(Link.getInstance().getLink().get(i).getWebsite()));
                startActivity(nextActivity);
            }
        });
    }
}