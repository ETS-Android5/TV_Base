/**
 * DataWatch vai receber de HomeData e fazer o request com a url baseada
 * em id.*/
package com.a7apps.tvbase.data;

import android.content.Context;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.connection.Connection;
import com.a7apps.tvbase.database.DatabaseHelper;

import java.util.ArrayList;

public class DataWatch {
    private Context context;
    private ArrayList<String> listWatchlistMovies = new ArrayList<>();
    private ArrayList<String> listWatchedMovies = new ArrayList<>();
    private ArrayList<String> listWatchlistSeries = new ArrayList<>();
    private ArrayList<String> listWatchedSeries = new ArrayList<>();
    private ArrayList<String> idWatchlistMovies = new ArrayList<>();
    private ArrayList<String> idWatchedMovies = new ArrayList<>();
    private ArrayList<String> idWatchlistSeries = new ArrayList<>();
    private ArrayList<String> idWatchedSeries = new ArrayList<>();
    private Connection connection;
    private HomeData homeData;

    public DataWatch(Context context) {
        this.context = context;
        connection = new Connection(context);
        homeData = new HomeData(context);

    }

    public void initWatchedMovies(){
        for (int i = 0; i < homeData.getWatchedMovies().size(); i++){
            connection.thirdRequest(Constants.movieById(homeData.getWatchedMovies().get(i)), listWatchedMovies, idWatchedMovies);
        }
    }
    public void initWatchlistMovies(){
        for (int i = 0; i < homeData.getWatchlistMovies().size(); i++){
            connection.thirdRequest(Constants.movieById(homeData.getWatchlistMovies().get(i)),listWatchlistMovies, idWatchlistMovies);
        }
    }
    public void initWatchedSeries(){
       for (int i = 0; i < homeData.getWatchedSeries().size(); i++){
           connection.thirdRequest(Constants.serieById(homeData.getWatchedSeries().get(i)),listWatchedSeries,idWatchedSeries);
       }
    }
    public void initWatchlistSeries(){
        for (int i = 0; i < homeData.getWatchlistSeries().size(); i++){
            connection.thirdRequest(Constants.serieById(homeData.getWatchlistSeries().get(i)),listWatchlistSeries,idWatchlistSeries);
        }
    }
    public ArrayList<String> getListWatchedMovies() {
        return listWatchedMovies;
    }

    public ArrayList<String> getIdWatchedMovies() {
        return idWatchedMovies;
    }

    public ArrayList<String> getListWatchlistMovies() {
        return listWatchlistMovies;
    }

    public ArrayList<String> getIdWatchlistMovies() {
        return idWatchlistMovies;
    }

    public ArrayList<String> getListWatchlistSeries() {
        return listWatchlistSeries;
    }

    public ArrayList<String> getListWatchedSeries() {
        return listWatchedSeries;
    }

    public ArrayList<String> getIdWatchlistSeries() {
        return idWatchlistSeries;
    }

    public ArrayList<String> getIdWatchedSeries() {
        return idWatchedSeries;
    }
}
