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
        arrayList.add("Aden");
        arrayList.add("Adel");
        arrayList.add("Caro");
        arrayList.add("Eeri");
        arrayList.add("Ellis");
        arrayList.add("Emel");
        arrayList.add("Ilo");
        arrayList.add("Kaino");
        arrayList.add("Karli");
        arrayList.add("Kim");
        arrayList.add("Mille");
        arrayList.add("Selmi");
        arrayList.add("Syksy");
        arrayList.add("Tuisku");
        arrayList.add("Varma");
        arrayList.add("Lahja");
        arrayList.add("Krisse");
        arrayList.add("Kristen");
        arrayList.add("Airut");
        arrayList.add("Utu");
        arrayList.add("Paju");
        arrayList.add("Laine");
        arrayList.add("Toive");
        arrayList.add("Kuisma");
        arrayList.add("Pii");
        arrayList.add("Salama");
        arrayList.add("Puro");
        arrayList.add("Emi");
        arrayList.add("Hede");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}