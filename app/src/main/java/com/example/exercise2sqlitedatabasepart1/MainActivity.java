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
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDB;
    EditText editName, editSurname, editMarks, editId;
    Button btnAddData, btnViewAll, btnUpdate, btnDelete, btnCall, btnEmail, btnWebSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DataBaseHelper(this);

        editName = findViewById(R.id.editTextName);
        editSurname = findViewById(R.id.editTextSurname);
        editMarks = findViewById(R.id.editTextMarks);
        editId = findViewById(R.id.editTextId);
        btnAddData = findViewById(R.id.button_Add);
        btnViewAll = findViewById(R.id.button_ViewAll);
        btnUpdate = findViewById(R.id.button_Update);
        btnDelete = findViewById(R.id.button_Delete);
        btnCall = findViewById(R.id.button_Call);
        btnEmail = findViewById(R.id.button_Gmail);
        btnWebSite = findViewById(R.id.buttonWebSite);
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
        MakeCall();
        OpenEmailApp();
        OpenWebSite();
    }

    public void MakeCall(){

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0987654321"));
                startActivity(intent);
            }
        });
    }

    public void OpenEmailApp() {
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(intent);
            }
        });
    }

    public void OpenWebSite(){
        btnWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.jonaszanini.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    public void sendMail(){

    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer isDeleted = myDB.deleteData(editId.getText().toString());

                if (isDeleted > 0){
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data NOT deleted", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDB.updateData( editId.getText().toString(), editName.getText().toString(),
                        editSurname.getText().toString(), editMarks.getText().toString());

                if (isUpdated == true){
                    Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data NOT updated", Toast.LENGTH_LONG).show();
                }
            }
        });
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

                ShowMessage("Data", buffer.toString());

                //Intent intent = new Intent(MainActivity.this, ListStudents.class);
                //startActivity(intent);

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
