package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>
{
    // Member variables needed for the class
    List<String> items;

    // We need the a list as it is the model for our data
    public ItemsAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    // Responsible for creating each ViewHolder view in the RecyclerView
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use a layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // Wrap the view inside a ViewHolder and return it
        return new ViewHolder(todoView);
    }

    @Override
    // Responsible for taking information from a position and binding it to a particular view holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grab the item at the specified position (position parameter)
        String item = items.get(position);

        // Bind the item retrieved to the specific view holder (holder parameter)
        holder.bind(item);
    }

    @Override
    // Accessor method for telling the RV the number of items present in the list
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to the views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Creates a reference to the simple_list_item_1 TextView resource we are
            // using as a view in the view handler
            this.tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Updates the view inside of the view holder with the given data (item parameter)
        public void bind(String item) {
            tvItem.setText(item);
        }
    }
}
