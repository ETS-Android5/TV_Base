package com.a7apps.tvbase.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.connection.Connection;

import java.util.ArrayList;

public class DataPop {
    private Context context;
    private Connection connection;
    private ArrayList<String> dataPopMovies = new ArrayList<>();
    private ArrayList<String> dataPopSeries = new ArrayList<>();

    public DataPop(Context context) {
        this.context = context;
        connection = new Connection(context);
    }

    public ArrayList<String> getDataPopMovies() {
        connection.getPosters(Constants.getBaseMovieUrl(), dataPopMovies);
        return dataPopMovies;
    }

    public ArrayList<String> getDataPopSeries() {
        connection.getPosters(Constants.getBaseTvUrl(), dataPopSeries);
        return dataPopSeries;
    }

}
