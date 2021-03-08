package com.example.raskaussovellus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class MaleNamesActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_male);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Male names");

        listView =(ListView)findViewById(R.id.lvMale);

        /**
         * Creates ne list and add names to it
         */
        ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("Juhani");
        arrayList.add("Olavi");
        arrayList.add("Johannes");
        arrayList.add("Mikael");
        arrayList.add("Oliver");
        arrayList.add("Ilmari");
        arrayList.add("Juhani");
        arrayList.add("Onni");
        arrayList.add("Elias");
        arrayList.add("Eino");
        arrayList.add("Matias");
        arrayList.add("Antero");
        arrayList.add("Leo");
        arrayList.add("Toivo");
        arrayList.add("Väinö");
        arrayList.add("Viljami");
        arrayList.add("Tapio");
        arrayList.add("Tapani");
        arrayList.add("Oskari");
        arrayList.add("Emil");
        arrayList.add("Eemil");
        arrayList.add("Veikko");
        arrayList.add("Samuel");
        arrayList.add("Eemeli");
        arrayList.add("Benjamin");
        arrayList.add("Kristian");
        arrayList.add("Eeli");
        arrayList.add("Armas");
        arrayList.add("Armas");
        arrayList.add("Noel");
        arrayList.add("Alvar");
        arrayList.add("Vilho");
        arrayList.add("Kalevi");
        arrayList.add("Aatos");
        arrayList.add("Sakari");

        /**
         * list layout type
         */
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }
}