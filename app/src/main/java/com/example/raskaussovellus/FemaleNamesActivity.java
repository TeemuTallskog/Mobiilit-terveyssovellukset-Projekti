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
        arrayList.add("Maria");
        arrayList.add("Sofia");
        arrayList.add("Emilia");
        arrayList.add("Olivia");
        arrayList.add("Aino");
        arrayList.add("Helmi");
        arrayList.add("Matilda");
        arrayList.add("Ilona");
        arrayList.add("Amanda");
        arrayList.add("Linnea");
        arrayList.add("Ellen");
        arrayList.add("Lilja");
        arrayList.add("Aada");
        arrayList.add("Alina");
        arrayList.add("Eveliina");
        arrayList.add("Helena");
        arrayList.add("Alexandra");
        arrayList.add("Aava");
        arrayList.add("Aleksandra");
        arrayList.add("Eevi");
        arrayList.add("Isla");
        arrayList.add("Ella");
        arrayList.add("Kristiina");
        arrayList.add("Anna");
        arrayList.add("Inkeri");
        arrayList.add("Elsa");
        arrayList.add("Emma");
        arrayList.add("Elli");
        arrayList.add("Enni");
        arrayList.add("Katariina");
        arrayList.add("Kerttu");
        arrayList.add("Elina");
        arrayList.add("Viola");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }
}