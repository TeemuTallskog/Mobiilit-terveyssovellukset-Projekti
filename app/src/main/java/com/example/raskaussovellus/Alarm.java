package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;


public class Alarm extends AppCompatActivity {

    /**
     * present variable
     */
    TextView timeHour;
    TextView timeMinute;
    Button setAlarm;
    Button setTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        /**
         * this variables haves personal names
         */
        timeHour = findViewById(R.id.Hour);
        timeMinute = findViewById(R.id.Minute);
        setAlarm = findViewById(R.id.Alarm);
        setTime = findViewById(R.id.Time);

        /**
         * listener Set Time button
         */
        setTime.setOnClickListener((v) -> {
            /**
             * this method find ja set hour of day and minute.
             */
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);

            /**
             * the listener wait when button is pressed
             */
            timePickerDialog = new TimePickerDialog(Alarm.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // Edit Text shows hour and minute
                    timeHour.setText(String.format("%02d", hourOfDay));
                    timeMinute.setText(String.format("%02d", minute));
                }

                /**
                 * if this current hour and minute fails, timePickerDialog gives you new time
                 */
            }, currentHour, currentMinute, false);

            timePickerDialog.show();

        });

        /**
         * listener Set Alarm button
         */
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * if this working -> startActivity(intent) start, if not, nothing will happend.
                 */
                if (!timeHour.getText().toString().isEmpty() && !timeMinute.getText().toString().isEmpty()) {

                    /**
                     * this action start an Activity to set a new alarm or timer in an alarm clock application.
                     */
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(timeHour.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(timeMinute.getText().toString()));
                    /**
                     * and some message
                     */
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Time to write your feelings!");

                    startActivity(intent);

                }

            }

        });
    }
}