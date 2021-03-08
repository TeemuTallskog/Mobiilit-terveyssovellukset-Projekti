package com.example.raskaussovellus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

/**
 * adapter that puts CalendarData into log_row.xml layout
 */
public class LogHistoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<CalendarData> arrayList;

    public LogHistoryAdapter(Context context, ArrayList<CalendarData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.log_row, parent, false);

        TextView date = (TextView) convertView.findViewById(R.id.dateLog);
        ImageView mood = (ImageView) convertView.findViewById(R.id.emojiLog);
        TextView weight = (TextView) convertView.findViewById(R.id.weightLog);
        TextView custom = (TextView) convertView.findViewById(R.id.customLog);
        TextView yearView = (TextView) convertView.findViewById(R.id.yearLog);

        String customString = arrayList.get(position).getCustomData();
        String dateId = arrayList.get(position).getDateID();

        if(customString.length() > 30) {
            customString = customString.substring(0, Math.min(customString.length(), 30));
            customString = customString + "...";
        }
        custom.setText(customString);

        weight.setText(String.valueOf(arrayList.get(position).getWeight()) + " Kg");
        int moodInt = arrayList.get(position).getMood();
        setMoodImage(moodInt, mood);
        setFormattedDate(dateId,date,yearView);



        return convertView;
    }

    /**
     * turns date id (ex.20210307) into a readable format and sets it into a text view.
     * @param dateID
     * @param tv textview you want to set the date on
     * @param yeartv textview you want to set the year on
     */
    private void setFormattedDate(String dateID, TextView tv, TextView yeartv){
        String year = dateID.substring(0,4);
        String days = dateID.substring(6,8);
        String months = dateID.substring(4,6);
        String formatterDate = new DateFormatSymbols().getMonths()[Integer.parseInt(months) - 1] + " " + days;
        tv.setText(formatterDate);
        yeartv.setText(year);
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
}
