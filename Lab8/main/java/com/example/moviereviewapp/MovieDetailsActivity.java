package com.example.moviereviewapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView movieTitle, yearText, ratingText, reviewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        databaseHelper = new DatabaseHelper(this);
        movieTitle = findViewById(R.id.movieTitle);
        yearText = findViewById(R.id.yearText);
        ratingText = findViewById(R.id.ratingText);
        reviewText = findViewById(R.id.reviewText);

        String movieName = getIntent().getStringExtra("movie_name");
        loadMovieDetails(movieName);
    }

    private void loadMovieDetails(String movieName) {
        Cursor cursor = databaseHelper.getMovieReviews(movieName);
        if (cursor.moveToFirst()) {
            movieTitle.setText(cursor.getString(1));
            yearText.setText("Year: " + cursor.getInt(2));
            ratingText.setText("Rating: " + cursor.getInt(4) + "⭐");
            reviewText.setText("Review: " + cursor.getString(3));
        }
    }
}

