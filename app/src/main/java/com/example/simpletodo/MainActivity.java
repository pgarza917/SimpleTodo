package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText edItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;

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

        // Implementation of long click listener needed for communication with Items Adapter
        // class
        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                // Delete the item from the model at position
                items.remove(position);
                // Notify the adapter of the deletion
                itemsAdapter.notifyItemRemoved(position);
                // Create a Toast to notify user that item was deleted successfully
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
            }
        };

        // Creating a new item adapter to handle display of list data in rows of view handler
        itemsAdapter = new ItemsAdapter(items, onLongClickListener);

        // Giving the recycler view (RV) the items adapter we just created
        rvItems.setAdapter(itemsAdapter);

        // Giving the RV a layout manager that displays things in the UI in a vertical way
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        // Adds an on click listener to handle what to do when the button is clicked
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            // Reaching this method means someone has clicked/tapped on the button
            public void onClick(View view) {
                // Retrieves the content currently in the edit text view
                String todoText = edItem.getText().toString();
                // Add retrieved content to the model
                items.add(todoText);
                // Notify the adapter is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                // Clear edit text view once content is submitted into model
                edItem.setText("");
                // Create a Toast to let user know that content was submitted successfully
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}