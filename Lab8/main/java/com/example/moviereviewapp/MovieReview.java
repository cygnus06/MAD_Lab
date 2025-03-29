package com.example.moviereviewapp;

public class MovieReview {
    private int id;
    private String movieName;
    private int year;
    private String reviewText;
    private int rating;

    public MovieReview(int id, String movieName, int year, String reviewText, int rating) {
        this.id = id;
        this.movieName = movieName;
        this.year = year;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters
    public int getId() { return id; }
    public String getMovieName() { return movieName; }
    public int getYear() { return year; }
    public String getReviewText() { return reviewText; }
    public int getRating() { return rating; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public void setYear(int year) { this.year = year; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
    public void setRating(int rating) { this.rating = rating; }
}
