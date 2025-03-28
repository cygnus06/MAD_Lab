package com.example.lab8_q2;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TotalCostActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView totalCostText;
    ListView categoryListView;
    double totalCost = 0.0;
    ArrayList<HashMap<String, String>> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_cost);

        databaseHelper = new DatabaseHelper(this);
        totalCostText = findViewById(R.id.totalCostText);
        categoryListView = findViewById(R.id.categoryListView);

        loadItems();
    }

    private void loadItems() {
        Cursor cursor = databaseHelper.getAllItems();
        itemList.clear();
        totalCost = 0.0;

        while (cursor.moveToNext()) {
            String category = cursor.getString(1);
            String item = cursor.getString(2);
            double price = cursor.getDouble(3);
            totalCost += price;

            HashMap<String, String> map = new HashMap<>();
            map.put("category", category);
            map.put("item", item + " - $" + price);
            itemList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, itemList, android.R.layout.simple_list_item_2,
                new String[]{"category", "item"}, new int[]{android.R.id.text1, android.R.id.text2});
        categoryListView.setAdapter(adapter);

        totalCostText.setText("Total Cost: $" + totalCost);
    }
}
