package com.a7apps.tvbase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "streaming.db";
    private static final String TABLE_NAME = "media_table";
    private static final String TABLE_NAME2 = "media_table_movies";
    private static final String _watchlistMovies = "watchlistMovies";
    private static final String _watchedMovies = "watchedMovies";
    private static final String _watchlistSeries = "watchlistSeries";
    private static final String _watchedSeries = "watchedSeries";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //db.execSQL("create table " + TABLE_NAME + "(watchlistMovies VARCHAR, watchedMovies VARCHAR, watchlistSeries VARCHAR, watchedSeries VARCHAR)");
        db.execSQL("create table " + TABLE_NAME + "("+ _watchlistMovies +" VARCHAR, "+ _watchedMovies +" VARCHAR, "+ _watchlistSeries +" VARCHAR, "+ _watchedSeries +" VARCHAR)");
       // db.execSQL("create table "+TABLE_NAME+"(watchlistMovies VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DATABASE_NAME);
        onCreate(db);
    }

    public boolean insertWatchlistMovies(String watchlistMovies){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_watchlistMovies, watchlistMovies);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean insertWatchlistSeries(String watchlistSeries){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_watchlistSeries, watchlistSeries);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean insertWatchedMovies(String watchedMovies){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_watchedMovies, watchedMovies);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean insertWatchedSeries(String watchedSeries){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(_watchedSeries, watchedSeries);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getWatchlistMovies(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select "+_watchlistMovies+" from "+TABLE_NAME, null);

        return cursor;
    }

    public Cursor getWatchedMovies(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select "+_watchedMovies+" from "+TABLE_NAME, null);

        return cursor;
    }

    public Cursor getWatchlistSeries(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select "+_watchlistSeries+" from "+TABLE_NAME, null);

        return cursor;
    }

    public Cursor getWatchedSeries(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select "+_watchedSeries+" from "+TABLE_NAME, null);

        return cursor;
    }
}
