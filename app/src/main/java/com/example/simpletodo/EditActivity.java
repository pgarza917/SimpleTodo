package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        // Pre-populate the edit text view with data received from intent
        etItem.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));

        // Adds listener to button to handle clicks
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            // When the user is done editing, they click the save button
            public void onClick(View view) {
                // Create intent to contain user modifications (using intent as shell, not
                // launching new activity so no parameters needed)
                Intent intent = new Intent();

                // Pass the data (user edits)
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, etItem.getText().toString());
                intent.putExtra(MainActivity.KEY_ITEM_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));

                // Set the result of the intent
                setResult(RESULT_OK, intent);

                // Finish activity, close screen, and go back
                finish();
            }
        });
    }
}