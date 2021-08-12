package com.a7apps.tvbase.data;

import android.content.Context;
import com.a7apps.tvbase.database.DatabaseHelper;

import java.util.ArrayList;

public class DataWatch {
    private Context context;
    private ArrayList<String> listWatchlistMovies = new ArrayList<>();
    private ArrayList<String> listWatchedMovies = new ArrayList<>();
    private ArrayList<String> listWatchlistSeries = new ArrayList<>();
    private ArrayList<String> listWatchedSeries = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    public DataWatch(Context context) {
        this.context = context;
    }
}
