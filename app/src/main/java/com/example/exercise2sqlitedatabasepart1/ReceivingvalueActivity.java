package com.example.exercise2sqlitedatabasepart1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ReceivingvalueActivity extends AppCompatActivity {

    EditText receivedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivingvalue);

        receivedValue = findViewById(R.id.editTextReceivingValue);

        Intent i = this.getIntent();
        receivedValue.setText(i.getStringExtra("text"));
    }
}
