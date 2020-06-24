package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText edItem;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Member variables for each view added in the design of the app
        btnAdd = findViewById(R.id.btnAdd);
        edItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);

        // items will be a model for our data
        items = new ArrayList<>();

        // Mocking data in items list to start
        items.add("Buy juice");
        items.add("Go for a run");
        items.add("Have fun!");

        // Creating a new item adapter to handle display of list data in rows of view handler
        ItemsAdapter itemsAdapter = new ItemsAdapter(items);

        // Giving the recycler view (RV) the items adapter we just created
        rvItems.setAdapter(itemsAdapter);

        // Giving the RV a layout manager that displays things in the UI in a vertical way
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }
}