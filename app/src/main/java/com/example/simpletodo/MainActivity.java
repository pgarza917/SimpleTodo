package com.example.simpletodo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import org.apache.commons.io.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Keys to identify what type of data we are working with when moving to other activities
    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POSITION = "item_position";

    // Keys to distinguish between different activities other than Main Activity
    public static final int EDIT_TEXT_CODE = 20;

    List items;

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

        // Load saved items from stored data file into the list
        loadItems();

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
                saveItems();
            }
        };

        // Implementation of click listener needed for communication with Items Adapter
        // class
        ItemsAdapter.OnClickListener onClickListener = new ItemsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                // Create the activity
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                // Pass the data being edited
                i.putExtra(KEY_ITEM_TEXT, (String) items.get(position));
                i.putExtra(KEY_ITEM_POSITION, position);
                // Display the activity
                startActivityForResult(i, EDIT_TEXT_CODE);
            }
        };

        // Creating a new item adapter to handle display of list data in rows of view handler
        itemsAdapter = new ItemsAdapter(items, onLongClickListener, onClickListener);

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
                saveItems();
            }
        });
    }

    // handle the result of the Edit Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
            // Retrieve updated text value
            String itemText = data.getStringExtra(KEY_ITEM_TEXT);
            // Extract the original position of edited item from the position key
            int position = data.getExtras().getInt(KEY_ITEM_POSITION);

            // Update the model with retrieved itemText at correct position
            items.set(position, itemText);
            // Notify adapter that changes have been made
            itemsAdapter.notifyItemChanged(position);
            // Persist the changes
            saveItems();

            // Create Toast to notify user that their edits were made and saved successfully
            Toast.makeText(getApplicationContext(), "Item updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Log.w("MainActivity", "Unknown call to onActivityResult");
        }
    }

    // Method will return the file in which we will store our list of to-do items
    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }

    // This function will load items by reading every line of the data file
    private void loadItems() {
        try{
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        }
        catch(IOException e){
            Log.e("MainActivity", "Error reading items", e);
            items = new ArrayList<>();
        }
    }

    // This function saves items by writing them into the data file
    private void saveItems() {
        try{
            FileUtils.writeLines(getDataFile(), items);
        }
        catch(IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }
    }
}