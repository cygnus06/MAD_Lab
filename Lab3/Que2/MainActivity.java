package com.example.app3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List of sports
        String[] sports = {"Football", "Basketball", "Cricket", "Tennis", "Hockey", "Badminton", "Baseball", "Volleyball"};

        // Colors for each sport
        int[] colors = {
                Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                Color.CYAN, Color.MAGENTA, Color.GRAY, Color.LTGRAY
        };

        // Find views
        TextView textViewSelectedSport = findViewById(R.id.textViewSelectedSport);
        ListView listViewSports = findViewById(R.id.listViewSports);

        // Set up a custom ArrayAdapter to display sports with colors
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_sport, sports) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setBackgroundColor(colors[position]); // Set background color for each item
                return view;
            }
        };
        listViewSports.setAdapter(adapter);

        // Set an item click listener
        listViewSports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected sport
                String selectedSport = sports[position];

                // Update the TextView with the selected sport
                textViewSelectedSport.setText("Selected Sport: " + selectedSport);
            }
        });
    }
}
