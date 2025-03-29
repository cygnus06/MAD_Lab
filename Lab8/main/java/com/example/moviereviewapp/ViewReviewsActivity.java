package com.example.moviereviewapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;



public class ViewReviewsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText searchMovieInput, searchYearInput;
    Spinner ratingSpinner;
    Button searchButton;
    ListView reviewListView;
    ArrayList<String> reviewList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);

        databaseHelper = new DatabaseHelper(this);
        searchMovieInput = findViewById(R.id.searchMovieInput);
        searchYearInput = findViewById(R.id.searchYearInput);
        ratingSpinner = findViewById(R.id.ratingSpinner);
        searchButton = findViewById(R.id.searchButton);
        reviewListView = findViewById(R.id.reviewListView);

        loadReviews("", 0, 0);

        searchButton.setOnClickListener(v -> {
            String movieName = searchMovieInput.getText().toString().trim();
            int year = searchYearInput.getText().toString().isEmpty() ? 0 : Integer.parseInt(searchYearInput.getText().toString());
            int minRating = Integer.parseInt(ratingSpinner.getSelectedItem().toString());
            loadReviews(movieName, minRating, year);
        });

        reviewListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedReview = reviewList.get(position);
            Intent intent = new Intent(ViewReviewsActivity.this, MovieDetailsActivity.class);
            intent.putExtra("movie_name", selectedReview.split(" - ")[0]);
            startActivity(intent);
        });
    }

    private void loadReviews(String movieName, int minRating, int year) {
        Cursor cursor = databaseHelper.searchReviews(movieName, minRating, year);
        reviewList.clear();
        while (cursor.moveToNext()) {
            reviewList.add(cursor.getString(1) + " - " + cursor.getInt(4) + "⭐");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviewList);
        reviewListView.setAdapter(adapter);
    }
}
