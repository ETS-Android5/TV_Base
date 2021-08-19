package com.a7apps.tvbase.data;

import android.content.Context;
import android.database.Cursor;
import com.a7apps.tvbase.database.DatabaseHelper;

import java.util.ArrayList;

public class HomeData {
    private Context context;
    private ArrayList<String> watchlistMovies = new ArrayList<>();
    private ArrayList<String> watchedMovies = new ArrayList<>();
    private ArrayList<String> watchlistSeries = new ArrayList<>();
    private ArrayList<String> watchedSeries = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    public HomeData(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        fillWatchlistMovies();
        fillWatchlistSeries();
        fillWatchedMovies();
        fillWatchedSeries();
    }

    public void fillWatchlistMovies(){
        Cursor cursor = databaseHelper.getWatchlistMovies();

        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                String strId = cursor.getString(0);
                watchlistMovies.add(strId);
            }
        }
    }

    public void fillWatchedMovies(){
        Cursor cursor = databaseHelper.getWatchedMovies();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                String strId = cursor.getString(0);
                watchedMovies.add(strId);
            }
        }
    }

    public void fillWatchlistSeries(){
        Cursor cursor = databaseHelper.getWatchlistSeries();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                String strId = cursor.getString(0);
                watchlistSeries.add(strId);
            }
        }
    }

    public void fillWatchedSeries(){
        Cursor cursor = databaseHelper.getWatchedSeries();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                String strId = cursor.getString(0);
                watchedSeries.add(strId);
            }
        }
    }

    public ArrayList<String> getWatchlistMovies() {
        return watchlistMovies;
    }

    public ArrayList<String> getWatchedMovies() {
        return watchedMovies;
    }

    public ArrayList<String> getWatchlistSeries() {
        return watchlistSeries;
    }

    public ArrayList<String> getWatchedSeries() {
        return watchedSeries;
    }
}
