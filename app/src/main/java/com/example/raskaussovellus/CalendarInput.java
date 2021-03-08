package com.example.raskaussovellus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * Calendar input activity is used to add data logs into the database.
 */
public class CalendarInput extends AppCompatActivity {
    private String dateID;
    private CalendarData calendarData;
    private String selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_input);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Calendar Input");
        Intent intent = getIntent();
        String origin = intent.getStringExtra("Origin");
        Log.i("tag", origin);
        if(origin.equals("Main")){
            dateID = intent.getStringExtra(MainActivity.DATE_ID);
            selectedDate = intent.getStringExtra(MainActivity.DATE);
        }else if(origin.equals("Calendar")) {
            dateID = intent.getStringExtra(CalendarActivity.DATE_ID);
            selectedDate = intent.getStringExtra(CalendarActivity.DATE);
        }
        TextView displayDate = findViewById(R.id.dateView);
        displayDate.setText(selectedDate);
        Log.i("Tag",dateID);
        calendarData = new CalendarDatabase(this).getCalendarData(dateID);
        setDefaults(calendarData);
    }

    /**
     * Sets the default values on the input page incase the user is editing and earlier log.
     * @param cData Input parameter is the Calendardata object pulled from the database.
     *
     */
    private void setDefaults(CalendarData cData){
        int mood = cData.getMood();
        RadioGroup radioGroup = findViewById(R.id.emojiRadioGrp);
        if(mood != 0){
            switch (mood){
                case 4:
                    radioGroup.check(R.id.emojiBtn1);
                    break;

                case 3:
                    radioGroup.check(R.id.emojiBtn2);
                    break;

                case 2:
                    radioGroup.check(R.id.emojiBtn3);
                    break;

                case 1:
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
        } else if(Double.parseDouble(weightBox.getText().toString()) > 600){
            falseEntry("Entered weight is too big.");
            return;
        }else if(Double.parseDouble(weightBox.getText().toString()) < 0){
            falseEntry("Please enter a positive weight");
            return;
        }
        else{
            weight = Math.round(Double.parseDouble(weightBox.getText().toString())* 10) / 10.0;
        }

        if(customDataBox.getText().equals("")){
            customData = "Custom log";
        } else if (customDataBox.getText().length() > 300){
            falseEntry("Custom log is too long");
            return;
        }else{
            customData = customDataBox.getText().toString();
        }

        if(selectedRadioBtn == R.id.emojiBtn1){
            mood = 4;
        }else if(selectedRadioBtn == R.id.emojiBtn2){
            mood = 3;
        }else if (selectedRadioBtn == R.id.emojiBtn3){
            mood = 2;
        }else if(selectedRadioBtn == R.id.emojiBtn4){
            mood = 1;
        }else{
            mood = 0;
        }

        db.addData(new CalendarData(dateID, customData, weight, mood));
        startActivity(intent);
    }

    /**
     *  checks if a string contains a valid number
     *  source: https://www.baeldung.com/java-check-string-number
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

    private void falseEntry(String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error").setMessage(error).setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}