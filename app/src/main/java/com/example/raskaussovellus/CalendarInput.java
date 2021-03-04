package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Objects;

public class CalendarInput extends AppCompatActivity {
    private String dateID;
    private CalendarData calendarData;


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
        calendarData = new CalendarDatabase(this).getCalendarData(dateID);
        setDefaults(calendarData);
    }

    /**
     * Sets the default values on the input page incase the user is editing and earlier log.
     * @param cData Input parameter is the Calendardata object pulled from the database.
     *
     */
    public void setDefaults(CalendarData cData){
        int mood = cData.getMood();
        RadioGroup radioGroup = findViewById(R.id.emojiRadioGrp);
        if(mood != 0){
            switch (mood){
                case 1:
                    radioGroup.check(R.id.emojiBtn1);
                    break;

                case 2:
                    radioGroup.check(R.id.emojiBtn2);
                    break;

                case 3:
                    radioGroup.check(R.id.emojiBtn3);
                    break;

                case 4:
                    radioGroup.check(R.id.emojiBtn4);
                    break;
            }
        }
        else {
            radioGroup.check(R.id.emojiBtn2);
        }
        TextView textView = findViewById(R.id.inputCustomText);
        if(cData.getCustomData().equals("Custom log")){
            textView.setHint("");
        }else{
            textView.setText(cData.getCustomData());
        }
        TextView textView1 = findViewById(R.id.weightInput);
        if(cData.getWeight() == 0.0){
        }else{
            textView1.setText(String.valueOf(cData.getWeight()));
        }
    }

    /**
     * Submits the data to the database once called
     * @param view
     */
    public void submitData(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        TextView weightBox = findViewById(R.id.weightInput);
        TextView customDataBox = findViewById(R.id.inputCustomText);
        RadioGroup radioGroup = findViewById(R.id.emojiRadioGrp);
        int selectedRadioBtn = radioGroup.getCheckedRadioButtonId();
        CalendarDatabase db = new CalendarDatabase(this);

        double weight;
        String customData;
        int mood;

        boolean isNumber = isNumeric(weightBox.getText().toString());
        if(!isNumber){
            weight = 0.0;
        } else{
            weight = Math.round(Double.parseDouble(weightBox.getText().toString())* 10) / 10.0;
        }

        if(customDataBox.getText().equals("")){
            customData = "Custom log";
        } else{
            customData = customDataBox.getText().toString();
        }

        if(selectedRadioBtn == R.id.emojiBtn1){
            mood = 1;
        }else if(selectedRadioBtn == R.id.emojiBtn2){
            mood = 2;
        }else if (selectedRadioBtn == R.id.emojiBtn3){
            mood = 3;
        }else if(selectedRadioBtn == R.id.emojiBtn4){
            mood = 4;
        }else{
            mood = 0;
        }

        db.addData(new CalendarData(Integer.parseInt(dateID), customData, weight, mood));
        startActivity(intent);
    }

    /**
     *  checks if a string contains a valid number
     * @param str parameter is a string that might contain a number
     * @return returns true is the string contains a number and false if it doesn't
     */
    public boolean isNumeric(String str){
        if(str == null){
            return false;
        }
        try{
            double d = Double.parseDouble(str);
        }catch (NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }
}