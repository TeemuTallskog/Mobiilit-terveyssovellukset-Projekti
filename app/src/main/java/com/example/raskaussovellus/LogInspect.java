package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Objects;

public class LogInspect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_inspect);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        CalendarDatabase database = new CalendarDatabase(this);
        Intent intent = getIntent();
        int position = intent.getIntExtra(LogHistoryActivity.POSITION, 0);

        TextView dateTV = (TextView) findViewById(R.id.dateInspect);
        TextView weightTV = (TextView) findViewById(R.id.weightInspect);
        TextView customTV = (TextView) findViewById(R.id.customInspect);
        ImageView imageView = (ImageView) findViewById(R.id.emojiInspect);

        ArrayList<CalendarData> calendarData = database.getAllData();
        String date = calendarData.get(position).getDateID();
        String customData = calendarData.get(position).getCustomData();
        double weight = calendarData.get(position).getWeight();
        int mood = calendarData.get(position).getMood();
        weightTV.setText(String.valueOf(weight) + " Kg");
        customTV.setText(customData);
        setMoodImage(mood, imageView);
        setFormattedDate(date, dateTV);
    }

    /**
     * Sets the emoji according to moodInt
     * @param moodInt Mood integer from calendardata
     * @param mood Imageview for mood
     */
    private void setMoodImage(int moodInt, ImageView mood){
        switch (moodInt){
            case 1:
                mood.setImageResource(R.drawable.emoji_4_48);
                break;
            case 2:
                mood.setImageResource(R.drawable.emoji_3_48);
                break;
            case 3:
                mood.setImageResource(R.drawable.emoji_2_48);
                break;
            case 4:
                mood.setImageResource(R.drawable.emoji_1_48);
                break;
            case 0:
                mood.setImageResource(R.drawable.empty_mood_input);
                break;
        }
    }

    private void setFormattedDate(String dateID, TextView tv){
        String year = dateID.substring(0,4);
        String days = dateID.substring(6,8);
        String months = dateID.substring(4,6);
        String formatterDate = new DateFormatSymbols().getMonths()[Integer.parseInt(months) - 1] + " " + days + ", " + year;
        tv.setText(formatterDate);
    }
}