package com.example.raskaussovellus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this activity will handle click events.
        drawer = (NavigationView) findViewById(R.id.navigationView);
        drawer.setNavigationItemSelectedListener(this);

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

}