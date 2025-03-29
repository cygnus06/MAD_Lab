package com.example.lab8_q2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner categorySpinner, itemSpinner;
    TextView priceText;
    Button addButton, viewTotalButton;
    DatabaseHelper databaseHelper;

    String[] categories = {"Fruits", "Vegetables", "Liquids"};
    String[][] items = {{"Apple - $2", "Banana - $1"}, {"Carrot - $3", "Potato - $2"}, {"Milk - $5", "Juice - $4"}};
    double[][] prices = {{2, 1}, {3, 2}, {5, 4}};

    int selectedCategoryIndex = 0;
    int selectedItemIndex = 0;
    Button manageItemsButton;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.categorySpinner);
        itemSpinner = findViewById(R.id.itemSpinner);
        priceText = findViewById(R.id.priceText);
        addButton = findViewById(R.id.addButton);
        viewTotalButton = findViewById(R.id.viewTotalButton);
        databaseHelper = new DatabaseHelper(this);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categorySpinner.setAdapter(categoryAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryIndex = position;
                updateItemSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        manageItemsButton = findViewById(R.id.manageItemsButton);
        manageItemsButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ManageItemsActivity.class));
        });
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemIndex = position;
                priceText.setText("Price: $" + prices[selectedCategoryIndex][position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        addButton.setOnClickListener(v -> {
            String category = categories[selectedCategoryIndex];
            String item = items[selectedCategoryIndex][selectedItemIndex];
            double price = prices[selectedCategoryIndex][selectedItemIndex];

            databaseHelper.addItem(category, item, price);
            Toast.makeText(MainActivity.this, "Item Added!", Toast.LENGTH_SHORT).show();
        });

        viewTotalButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TotalCostActivity.class));
        });
    }

    private void updateItemSpinner() {
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items[selectedCategoryIndex]);
        itemSpinner.setAdapter(itemAdapter);
        priceText.setText("Price: $" + prices[selectedCategoryIndex][0]);
    }
}
