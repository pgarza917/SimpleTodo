package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    // Member variables for references to views we have in design
    EditText etItem;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Creating references to the views in our design
        etItem = findViewById(R.id.etItem);
        btnSave = findViewById(R.id.btnSave);

        // Making activity title appearing to user more descriptive
        getSupportActionBar().setTitle("Edit Item");
    }
}