package com.a7apps.tvbase.data;

import android.content.Context;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.connection.Connection;

import java.util.ArrayList;

public class DataPop {
    private Context context;
    private Connection connection;
    private ArrayList<String> posterPopMovies = new ArrayList<>();
    private ArrayList<String> posterPopSeries = new ArrayList<>();
    private ArrayList<String> moviesId = new ArrayList<>();
    private ArrayList<String> seriesId = new ArrayList<>();

    public DataPop(Context context) {
        this.context = context;
        connection = new Connection(context);
    }

    public void initDataMovies(){
        connection.primaryRequest(Constants.getBaseMovieUrl(), posterPopMovies, moviesId);
    }

    public void initDataSeries(){
        connection.primaryRequest(Constants.getBaseTvUrl(), posterPopSeries, seriesId);
    }

    public ArrayList<String> getPosterPopMovies() {
        return posterPopMovies;
    }

    public ArrayList<String> getPosterPopSeries() {
        return posterPopSeries;
    }

    public ArrayList<String> getMoviesId() {
        return moviesId;
    }

    public ArrayList<String> getSeriesId() {
        return seriesId;
    }
}
