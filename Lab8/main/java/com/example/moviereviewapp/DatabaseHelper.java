package com.example.moviereviewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MovieReviews.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REVIEWS = "reviews";
    private static final String COL_ID = "id";
    private static final String COL_MOVIE = "movie_name";
    private static final String COL_YEAR = "year";
    private static final String COL_REVIEW = "review_text";
    private static final String COL_RATING = "rating";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_REVIEWS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MOVIE + " TEXT, " +
                COL_YEAR + " INTEGER, " +
                COL_REVIEW + " TEXT, " +
                COL_RATING + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(db);
    }

    public boolean insertReview(String movieName, int year, String review, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MOVIE, movieName);
        values.put(COL_YEAR, year);
        values.put(COL_REVIEW, review);
        values.put(COL_RATING, rating);

        long result = db.insert(TABLE_REVIEWS, null, values);
        return result != -1;
    }

    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT DISTINCT " + COL_MOVIE + ", " + COL_YEAR + " FROM " + TABLE_REVIEWS, null);
    }

    public Cursor getMovieReviews(String movieName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_REVIEWS + " WHERE " + COL_MOVIE + "=?", new String[]{movieName});
    }

    public Cursor searchReviews(String movieName, int minRating, int year) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_REVIEWS + " WHERE 1=1";
        if (!movieName.isEmpty()) {
            query += " AND " + COL_MOVIE + " LIKE '%" + movieName + "%'";
        }
        if (minRating > 0) {
            query += " AND " + COL_RATING + " >= " + minRating;
        }
        if (year > 0) {
            query += " AND " + COL_YEAR + " = " + year;
        }
        return db.rawQuery(query, null);
    }

    public MovieReview getReviewById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, movie_name, year, review_text, rating FROM reviews WHERE id=?",
                new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            int reviewId = cursor.getInt(0);
            String movieName = cursor.getString(1);
            int year = cursor.getInt(2);
            //String reviewText = cursor.isNull(3) ? "" : cursor.getString(3); // Avoid null issue
            String reviewText = cursor.isNull(3) ? "" : cursor.getString(3);

            int rating = cursor.getInt(4);

            cursor.close();
            return new MovieReview(reviewId, movieName, year, reviewText, rating);
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public void updateReview(int id, String reviewText, int year, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REVIEW, reviewText);
        values.put(COL_YEAR, year);
        values.put(COL_RATING, rating);

        db.update(TABLE_REVIEWS, values, COL_ID + "=?", new String[]{String.valueOf(id)});
    }
    public Cursor getAllReviews() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id, movie_name, year, review_text, rating FROM reviews", null);
    }

    public void deleteReview(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REVIEWS, COL_ID + "=?", new String[]{String.valueOf(id)});
    }



}
