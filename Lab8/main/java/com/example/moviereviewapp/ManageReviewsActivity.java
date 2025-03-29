package com.example.moviereviewapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManageReviewsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView manageReviewListView;
    ArrayList<String> reviewList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<Integer> reviewIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reviews);

        databaseHelper = new DatabaseHelper(this);
        manageReviewListView = findViewById(R.id.manageReviewListView);

        loadReviews();

        manageReviewListView.setOnItemClickListener((parent, view, position, id) -> {
            int reviewId = reviewIds.get(position);
            showEditDeleteDialog(reviewId);
        });
    }

    private void loadReviews() {
        Cursor cursor = databaseHelper.getAllReviews();
        reviewList.clear();
        reviewIds.clear();

        while (cursor.moveToNext()) {
            reviewIds.add(cursor.getInt(0));  // Store review ID
            reviewList.add(cursor.getString(1) + " - " + cursor.getInt(4) + "⭐");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reviewList);
        manageReviewListView.setAdapter(adapter);
    }

    private void showEditDeleteDialog(int reviewId) {
        new AlertDialog.Builder(this)
                .setTitle("Manage Review")
                .setMessage("Do you want to edit or delete this review?")
                .setPositiveButton("Edit", (dialog, which) -> {
                    Intent intent = new Intent(ManageReviewsActivity.this, EditReviewActivity.class);
                    intent.putExtra("review_id", reviewId);
                    startActivity(intent);
                })
                .setNegativeButton("Delete", (dialog, which) -> {
                    databaseHelper.deleteReview(reviewId);
                    loadReviews();
                    Toast.makeText(this, "Review deleted!", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Cancel", null)
                .show();
    }
}

