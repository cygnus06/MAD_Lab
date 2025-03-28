package com.example.lab8_q2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GroceryDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "grocery_items";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_ITEM = "item";
    private static final String COLUMN_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_ITEM + " TEXT, " +
                COLUMN_PRICE + " REAL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addItem(String category, String item, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_ITEM, item);
        values.put(COLUMN_PRICE, price);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();

        if (deletedRows > 0) {
            System.out.println("Item deleted successfully: ID = " + id);
        } else {
            System.out.println("Item NOT deleted! ID = " + id);
        }
    }




    public void updateItem(int id, String category, String item, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_ITEM, item);
        values.put(COLUMN_PRICE, price);
        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public HashMap<String, Double> getCategoryTotal() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT category, SUM(price) FROM " + TABLE_NAME + " GROUP BY category", null);

        HashMap<String, Double> categoryTotals = new HashMap<>();
        if (cursor.moveToFirst()) {
            do {
                categoryTotals.put(cursor.getString(0), cursor.getDouble(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categoryTotals;
    }

    public double getTotalCost() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(price) FROM " + TABLE_NAME, null);
        double total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }
}
