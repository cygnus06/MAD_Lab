package com.example.moviereviewapp;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditReviewActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText editReviewText, editYearInput;
    Spinner editRatingSpinner;
    Button updateButton;
    int reviewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        databaseHelper = new DatabaseHelper(this);
        editReviewText = findViewById(R.id.editReviewText);
        editYearInput = findViewById(R.id.editYearInput);
        editRatingSpinner = findViewById(R.id.editRatingSpinner);
        updateButton = findViewById(R.id.updateButton);

        reviewId = getIntent().getIntExtra("review_id", -1);
        loadReviewDetails();


        updateButton.setOnClickListener(v -> updateReview());
    }

    private void loadReviewDetails() {
        MovieReview review = databaseHelper.getReviewById(reviewId);

        if (review != null) {
            editReviewText.setText(review.getReviewText());
            editYearInput.setText(String.valueOf(review.getYear()));

            // Make sure rating value is valid before setting it in Spinner
            if (review.getRating() > 0 && review.getRating() <= 5) {
                editRatingSpinner.setSelection(review.getRating() - 1);
            }
        } else {
            Toast.makeText(this, "Error: Review not found!", Toast.LENGTH_SHORT).show();
        }
    }



    private void updateReview() {
        String newReviewText = editReviewText.getText().toString();
        int newYear = Integer.parseInt(editYearInput.getText().toString());
        int newRating = Integer.parseInt(editRatingSpinner.getSelectedItem().toString());

        databaseHelper.updateReview(reviewId, newReviewText, newYear, newRating);
        Toast.makeText(this, "Review updated!", Toast.LENGTH_SHORT).show();
        finish();
    }
}

