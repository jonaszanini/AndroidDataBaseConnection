package com.example.exercise2sqlitedatabasepart1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDB;
    EditText editName, editSurname, editMarks;
    Button btnAddData, btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DataBaseHelper(this);

        editName = findViewById(R.id.editTextName);
        editSurname = findViewById(R.id.editTextSurname);
        editMarks = findViewById(R.id.editTextMarks);
        btnAddData = findViewById(R.id.button_Add);
        btnViewAll = findViewById(R.id.button_ViewAll);
        AddData();
        ViewAll();
    }

    public void ViewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();

                if (res.getCount() == 0){

                    ShowMessage("Error", "No data found");

                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()){
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Surname: " + res.getString(2) + "\n");
                    buffer.append("Marks: " + res.getString(3) + "\n\n");
                }

                //ShowMessage("Data", buffer.toString());

                Intent intent = new Intent(MainActivity.this, ListStudents.class);
                startActivity(intent);

            }
        });
    }

    public void ShowMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());

                if (isInserted == true){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data NOT inserted", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
