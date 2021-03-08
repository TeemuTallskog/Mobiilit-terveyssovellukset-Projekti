package com.example.raskaussovellus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Pulls data from the databse and puts it on A GraphView.
 */
public class DataAnalysis extends AppCompatActivity {

    private GraphView graphView;
    private LineGraphSeries<DataPoint> lineGraphSeries;
    private PointsGraphSeries<DataPoint> pointsGraphSeries;
    private BarGraphSeries<DataPoint> barGraphSeries;
    private DataPoint[] dataPoints;
    private DataPoint[] moodDataPoints;
    private SimpleDateFormat sdf;
    private RadioGroup radioGroup;
    private RadioGroup moodRadioGroup;
    private long todayInMillis;
    private long dayInMillis;
    private long monthInMillis;
    private long threeMonthsInMillis;
    private long sixMonthsInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Graphs");
        Calendar date = Calendar.getInstance();
        todayInMillis = date.getTimeInMillis() + TimeUnit.DAYS.toMillis(1);
        graphView =(GraphView) findViewById(R.id.lineChart);
        sdf = new SimpleDateFormat("dd/MM");

        getDatabaseData();

        dayInMillis = TimeUnit.DAYS.toMillis(1);
        monthInMillis = TimeUnit.DAYS.toMillis(30);
        threeMonthsInMillis = TimeUnit.DAYS.toMillis(90);
        sixMonthsInMillis = TimeUnit.DAYS.toMillis(180);

        lineGraphSeries = new LineGraphSeries<>(dataPoints);
        pointsGraphSeries = new PointsGraphSeries<>(dataPoints);
        barGraphSeries = new BarGraphSeries<>(moodDataPoints);


        graphView.getViewport().setScrollable(true);
        graphView.getGridLabelRenderer().setPadding(32);

        setListeners();

        radioGroup = findViewById(R.id.dateSelectRadio);
        moodRadioGroup = findViewById(R.id.DataDisplayRadioGroup);


        setRadioListeners();
        viewLimit(true);
        getWeightAverage();
        barGraphColor();
    }

    /**
     * retrieves the datapoints from the database.
     * this is called onCreate
     */
    private void getDatabaseData(){
        CalendarDatabase db = new CalendarDatabase(this);
        try {
            dataPoints = db.getWeight();
            moodDataPoints = db.getMood();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sets the radioGroup listeners
     * this is called onCreate
     */
    private void setRadioListeners(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(moodRadioGroup.getCheckedRadioButtonId() == R.id.WeightRadioBtn) {
                    getWeightAverage();
                    viewLimit(true);
                }
                if(moodRadioGroup.getCheckedRadioButtonId() == R.id.MoodRadioBtn){
                    viewLimit(false);
                }
            }
        });
        moodRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(moodRadioGroup.getCheckedRadioButtonId() == R.id.WeightRadioBtn) {
                    getWeightAverage();
                    viewLimit(true);
                }
                if(moodRadioGroup.getCheckedRadioButtonId() == R.id.MoodRadioBtn){
                    viewLimit(false);
                }
            }
        });

    }

    /**
     * Contains formatLabel which turns long date values into readable day/month values on the x axis.
     * Contains a data point tap listener which shows the date and value when a data point is clicked.
     * this is called onCreate
     */
    private void setListeners(){
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX){
                if(isValueX){
                    return sdf.format(new Date((long)value));
                }else{
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        lineGraphSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String msg = "Date: " + sdf.format(new Date((long)dataPoint.getX()))+ "\nKg: "+ dataPoint.getY();
                Toast.makeText(DataAnalysis.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        barGraphSeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String msg = "Date: " + sdf.format(new Date((long)dataPoint.getX()));
                Toast.makeText(DataAnalysis.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Limits the graphview to the last 30,90,180 days or shows all data points
     * @param isWeightData parameter is true if the data being limited is weight data and false if it is mood data.
     */
    private void viewLimit(Boolean isWeightData){
        double minXDataPoint;
        if(dataPoints.length != 0 || moodDataPoints.length != 0) {
            if(isWeightData){
                minXDataPoint = dataPoints[0].getX();
            }else{
                minXDataPoint = moodDataPoints[0].getX();
            }
            graphView.removeAllSeries();
            Viewport viewport = graphView.getViewport();
            int selectedId = radioGroup.getCheckedRadioButtonId();
            double minYval = viewport.getMinY(true);
            double maxYval = viewport.getMaxY(true);

            if (selectedId == R.id.ThirtyRadio) {
                long minDate = todayInMillis - monthInMillis;
                viewport.setMinX(minDate);
                viewport.setMaxX(todayInMillis);
            } else if (selectedId == R.id.NinetyRadio) {
                long minDate = todayInMillis - threeMonthsInMillis;
                viewport.setMinX(minDate);
                viewport.setMaxX(todayInMillis);
            } else if (selectedId == R.id.HalfYearRadio) {
                long minDate = todayInMillis - sixMonthsInMillis;
                viewport.setMinX(minDate);
                viewport.setMaxX(todayInMillis);
            } else if (selectedId == R.id.AllTimeRadio) {
                viewport.setMinX(minXDataPoint - dayInMillis);
                viewport.setMaxX(todayInMillis);
                graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
            }
            viewport.setXAxisBoundsManual(true);
            addSeriesLimist();
        }
    }


    /**
     * adds the Graph series's to the view graph and sets some limiters.
     * this methos is called inside viewLimit
     */
    private void addSeriesLimist(){
        Viewport viewport = graphView.getViewport();
        if(moodRadioGroup.getCheckedRadioButtonId() == R.id.WeightRadioBtn){
            viewport.setYAxisBoundsManual(false);
            graphView.addSeries(lineGraphSeries);
            graphView.addSeries(pointsGraphSeries);
            graphView.getGridLabelRenderer().setVerticalLabelsVisible(true);
        }
        if(moodRadioGroup.getCheckedRadioButtonId() == R.id.MoodRadioBtn){
            viewport.setMinY(0);
            viewport.setMaxY(6);
            viewport.setYAxisBoundsManual(true);
            graphView.getGridLabelRenderer().setVerticalLabelsVisible(false);
            graphView.addSeries(barGraphSeries);
            TextView textView = (TextView) findViewById(R.id.DataString);
            textView.setText("");
        }
    }

    /**
     * Sets the colors for the bargraph based on mood
     */
    private void barGraphColor(){
        barGraphSeries.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                if(data.getY()>3.1){
                    return Color.rgb(0,255,0);
                }else if(data.getY()>2.1 && data.getY()<3.1){
                    return Color.rgb(200,255,0);
                }else if(data.getY()>1.1 && data.getY()<2.1){
                    return Color.rgb(255,200,0);
                }else {
                    return Color.rgb(255,0,0);
                }
            }
        });
    }


    /**
     * Gets the weight averages based on what radio button is checked.
     */
    private void getWeightAverage(){
        if(dataPoints.length != 0) {
            int weightLenght = dataPoints.length;
            double weightTotal = 0;
            double weightAverage = 0;
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String outputString = "";

            if (selectedId == R.id.ThirtyRadio) {
                for (int i = 0; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() < todayInMillis + dayInMillis && dataPoints[i].getX() > todayInMillis - monthInMillis) {
                        weightTotal = weightTotal + dataPoints[i].getY();
                    } else {
                        weightLenght = weightLenght - 1;
                    }
                    outputString = "Your average weight in the past 30 days: ";
                }
            } else if (selectedId == R.id.NinetyRadio) {
                for (int i = 0; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() < todayInMillis + dayInMillis && dataPoints[i].getX() > todayInMillis - threeMonthsInMillis) {
                        weightTotal = weightTotal + dataPoints[i].getY();
                    } else {
                        weightLenght = weightLenght - 1;
                    }
                    outputString = "Your weight in the past 90 days: ";
                }
            } else if (selectedId == R.id.HalfYearRadio) {
                for (int i = 0; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() < todayInMillis + dayInMillis && dataPoints[i].getX() > todayInMillis - sixMonthsInMillis) {
                        weightTotal = weightTotal + dataPoints[i].getY();
                    } else {
                        weightLenght = weightLenght - 1;
                    }
                    outputString = "Your average weight in the past 180 days: ";
                }

            } else if (selectedId == R.id.AllTimeRadio) {
                for (int i = 0; i < dataPoints.length; i++) {
                    weightTotal = weightTotal + dataPoints[i].getY();
                }
                outputString = "Your all time weight average: ";
            }
            weightAverage = weightTotal / weightLenght;
            weightAverage = Math.round(weightAverage * 10) / 10.0;
            TextView textView = (TextView) findViewById(R.id.DataString);
            textView.setText(outputString + weightAverage + " Kg");
        }
    }

}
