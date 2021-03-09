package com.example.raskaussovellus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * displays a list of popular male names
 */
public class MaleNamesActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_male);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Male names");

        listView =(ListView)findViewById(R.id.lvMale);

        //Creates ArrayList and add name strings to it
        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("\nJuhani\n");
        arrayList.add("\nOlavi\n");
        arrayList.add("\nJohannes\n");
        arrayList.add("\nMikael\n");
        arrayList.add("\nOliver\n");
        arrayList.add("\nIlmari\n");
        arrayList.add("\nJuhani\n");
        arrayList.add("\nOnni\n");
        arrayList.add("\nElias\n");
        arrayList.add("\nEino\n");
        arrayList.add("\nMatias\n");
        arrayList.add("\nAntero\n");
        arrayList.add("\nLeo\n");
        arrayList.add("\nToivo\n");
        arrayList.add("\nVäinö\n");
        arrayList.add("\nViljami\n");
        arrayList.add("\nTapio\n");
        arrayList.add("\nTapani\n");
        arrayList.add("\nOskari\n");
        arrayList.add("\nEmil\n");
        arrayList.add("\nEemil\n");
        arrayList.add("\nVeikko\n");
        arrayList.add("\nSamuel\n");
        arrayList.add("\nEemeli\n");
        arrayList.add("\nBenjamin\n");
        arrayList.add("\nKristian\n");
        arrayList.add("\nEeli\n");
        arrayList.add("\nArmas\n");
        arrayList.add("\nArmas\n");
        arrayList.add("\nNoel\n");
        arrayList.add("\nAlvar\n");
        arrayList.add("\nVilho\n");
        arrayList.add("\nKalevi\n");
        arrayList.add("\nAatos\n");
        arrayList.add("\nSakari\n");


        //list layout type
        arrayAdapter = new ArrayAdapter(this, R.layout.link, arrayList);
        listView.setAdapter(arrayAdapter);

    }
}