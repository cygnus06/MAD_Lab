package com.example.lab8_q2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class GroceryAdapter extends ArrayAdapter<GroceryItem> {
    private Context context;
    private ArrayList<GroceryItem> items;
    private DatabaseHelper databaseHelper;

    public GroceryAdapter(Context context, ArrayList<GroceryItem> items, DatabaseHelper dbHelper) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        this.databaseHelper = dbHelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        GroceryItem item = getItem(position);
        TextView itemText = convertView.findViewById(R.id.itemText);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        itemText.setText(item.getCategory() + ": " + item.getName() + " - $" + item.getPrice());

        deleteButton.setOnClickListener(v -> {
            databaseHelper.deleteItem(item.getId());
            items.remove(position);
            notifyDataSetChanged();
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
