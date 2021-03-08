package com.example.raskaussovellus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FemaleNamesActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_names);

        listView =(ListView)findViewById(R.id.lvFemale);

        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("\nMaria\n");
        arrayList.add("\nSofia\n");
        arrayList.add("\nEmilia\n");
        arrayList.add("\nOlivia\n");
        arrayList.add("\nAino\n");
        arrayList.add("\nHelmi\n");
        arrayList.add("\nMatilda\n");
        arrayList.add("\nIlona\n");
        arrayList.add("\nAmanda\n");
        arrayList.add("\nLinnea\n");
        arrayList.add("\nEllen\n");
        arrayList.add("\nLilja\n");
        arrayList.add("\nAada\n");
        arrayList.add("\nAlina\n");
        arrayList.add("\nEveliina\n");
        arrayList.add("\nHelena\n");
        arrayList.add("\nAlexandra\n");
        arrayList.add("\nAava\n");
        arrayList.add("\nAleksandra\n");
        arrayList.add("\nEevi\n");
        arrayList.add("\nIsla\n");
        arrayList.add("\nElla\n");
        arrayList.add("\nKristiina\n");
        arrayList.add("\nAnna\n");
        arrayList.add("\nInkeri\n");
        arrayList.add("\nElsa\n");
        arrayList.add("\nEmma\n");
        arrayList.add("\nElli\n");
        arrayList.add("\nEnni\n");
        arrayList.add("\nKatariina\n");
        arrayList.add("\nKerttu\n");
        arrayList.add("\nElina\n");
        arrayList.add("\nViola\n");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.link, arrayList);
        listView.setAdapter(arrayAdapter);

    }
}