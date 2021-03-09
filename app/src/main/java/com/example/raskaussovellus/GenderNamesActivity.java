package com.example.raskaussovellus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

/**
 * Shows A list view with genders to choose from.
 */
public class GenderNamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setTitle("Newborn Names");

        //name object refers to singleton
        //setAdapter method gets the list and sets the correct list view
        GenderSingleton name = GenderSingleton.getInstanceGender();
        ListView lv = findViewById(R.id.genderLv);
        lv.setAdapter(new ArrayAdapter<Genders>(
                this,
                R.layout.link,
                name.getGenders())
        );

        /**
         * onClick listener checks what index in the list is clicked
         * and launch the corresponding activity
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 0){
                    Intent nextActivity = new Intent(GenderNamesActivity.this, FemaleNamesActivity.class);
                    startActivity(nextActivity);
                }
                else if(i == 1){
                    Intent nextActivity = new Intent(GenderNamesActivity.this, MaleNamesActivity.class);
                    startActivity(nextActivity);
                }else{
                    Intent nextActivity = new Intent(GenderNamesActivity.this, NeutralNamesActivity.class);
                    startActivity(nextActivity);
                }
            }
        });
    }
}