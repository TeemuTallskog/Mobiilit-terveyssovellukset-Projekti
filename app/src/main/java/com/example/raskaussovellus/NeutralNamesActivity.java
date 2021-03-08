package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NeutralNamesActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neutral_names);

        listView =(ListView)findViewById(R.id.LvNeutral);

        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("\nAden\n");
        arrayList.add("\nAdel\n");
        arrayList.add("\nCaro\n");
        arrayList.add("\nEeri\n");
        arrayList.add("\nEllis\n");
        arrayList.add("\nEmel\n");
        arrayList.add("\nIlo\n");
        arrayList.add("\nKaino\n");
        arrayList.add("\nKarli\n");
        arrayList.add("\nKim\n");
        arrayList.add("\nMille\n");
        arrayList.add("\nSelmi\n");
        arrayList.add("\nSyksy\n");
        arrayList.add("\nTuisku\n");
        arrayList.add("\nVarma\n");
        arrayList.add("\nLahja\n");
        arrayList.add("\nKrisse\n");
        arrayList.add("\nKristen\n");
        arrayList.add("\nAirut\n");
        arrayList.add("\nUtu\n");
        arrayList.add("\nPaju\n");
        arrayList.add("\nLaine\n");
        arrayList.add("\nToive\n");
        arrayList.add("\nKuisma\n");
        arrayList.add("\nPii\n");
        arrayList.add("\nSalama\n");
        arrayList.add("\nPuro\n");
        arrayList.add("\nEmi\n");
        arrayList.add("\nHede\n");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.link, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}