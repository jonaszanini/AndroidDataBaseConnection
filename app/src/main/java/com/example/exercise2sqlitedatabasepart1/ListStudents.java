package com.example.exercise2sqlitedatabasepart1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListStudents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        DataBaseHelper db = new DataBaseHelper(this);

        ListView listView = findViewById(R.id.listViewStudents);

        ArrayList<String> list = new ArrayList<>();

        Cursor data = db.getAllData();

        if (data.getCount() == 0){

            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();

        }
        else {
            while (data.moveToNext()){
                list.add(data.getString(1));
            }

            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

            listView.setAdapter(listAdapter);
        }
    }
}
