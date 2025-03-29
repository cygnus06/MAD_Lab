package com.example.moviereviewapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView movieListView;
    Button viewReviewsButton, writeReviewButton;
    ArrayList<String> movieList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        movieListView = findViewById(R.id.movieListView);
        viewReviewsButton = findViewById(R.id.viewReviewsButton);
        writeReviewButton = findViewById(R.id.writeReviewButton);

        loadMovies();

        movieListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedMovie = movieList.get(position);
            Intent intent = new Intent(MainActivity.this, WriteReviewActivity.class);
            intent.putExtra("movie_name", selectedMovie);
            startActivity(intent);
        });

        viewReviewsButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ViewReviewsActivity.class));
        });

        writeReviewButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WriteReviewActivity.class));
        });
        Button manageReviewsButton = findViewById(R.id.manageReviewsButton);
        manageReviewsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ManageReviewsActivity.class);
            startActivity(intent);
        });

    }

    private void loadMovies() {
        Cursor cursor = databaseHelper.getAllMovies();
        movieList.clear();
        while (cursor.moveToNext()) {
            String movie = cursor.getString(0) + " (" + cursor.getInt(1) + ")";
            movieList.add(movie);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieList);
        movieListView.setAdapter(adapter);
    }
}
