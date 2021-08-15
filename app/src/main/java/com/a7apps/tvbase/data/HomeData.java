package com.a7apps.tvbase.data;

import android.content.Context;
import android.database.Cursor;
import com.a7apps.tvbase.database.DatabaseHelper;

import java.util.ArrayList;

public class HomeData {
    private Context context;
    private ArrayList<String> watchlistMovie = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    public HomeData(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        fillWatchlistMovie();
    }

    public void fillWatchlistMovie(){
        Cursor cursor = databaseHelper.getWatchlistMovies();

        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                String strImg = cursor.getString(0);
                watchlistMovie.add(strImg);
            }
        }
    }

    public ArrayList<String> getWatchlistMovie() {
        return watchlistMovie;
    }
}
