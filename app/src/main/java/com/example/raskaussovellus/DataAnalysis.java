package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class DataAnalysis extends AppCompatActivity {

    GraphView graphView;
    LineGraphSeries<DataPoint> lineGraphSeries;
    PointsGraphSeries<DataPoint> pointsGraphSeries;
    DataPoint[] dataPoints;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CalendarDatabase db = new CalendarDatabase(this);

        graphView =(GraphView) findViewById(R.id.lineChart);
        sdf = new SimpleDateFormat("dd/MM");

        try {
            dataPoints = db.getWeight();
        } catch (Exception e) {
            e.printStackTrace();
        }

        lineGraphSeries = new LineGraphSeries<>(dataPoints);
        pointsGraphSeries = new PointsGraphSeries<>(dataPoints);
        graphView.addSeries(lineGraphSeries);
        graphView.addSeries(pointsGraphSeries);

        graphView.getViewport().setScrollable(true);
        setListeners();
    }




    public void setListeners(){
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
    }
}
