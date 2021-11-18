//Talvez mudar o nome desse package para common (se possivel)
package com.a7apps.tvbase.assistant;

import android.content.Context;
import android.widget.Toast;
import com.a7apps.tvbase.data.HomeData;
import com.android.volley.*;

public class AssistantMethods {
    private Context context;
    private HomeData homeData;

    public AssistantMethods(Context context) {
        this.context = context;
    }

    public void printConnectionError(VolleyError objError){
        String message = "";
        if (objError instanceof NetworkError){
            message = "Cannot getPosters to Internet...\nPlease check your connection!(NetworkError)";
        }else if(objError instanceof ServerError){
            message = "The server could not be found. Please try again after some time!!";
        }else if (objError instanceof AuthFailureError){
            message = "Cannot getPosters to Internet...Please check your connection!(AuthFailureError)";
        }else if (objError instanceof ParseError){
            message = "Parsing error! Please try again after some time!!";
        }else if (objError instanceof NoConnectionError){
            message = "Cannot getPosters to Internet...Please check your connection! (NoConnectionError)";
        }else if (objError instanceof TimeoutError){
            message = "Connection TimeOut! Please check your internet connection.";
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void checkDb(String key){
        homeData = new HomeData(context);
        if (homeData.getWatchlistMovies().size() == 0 && key.equals("MovieWatchlist")){
            Toast.makeText(context,"Adicione filmes a Watchlist!",Toast.LENGTH_SHORT).show();
        }
        if (homeData.getWatchedMovies().size() == 0 && key.equals("MovieWatched")){
            Toast.makeText(context,"Adicione filmes a Watched!",Toast.LENGTH_SHORT).show();
        }
        if (homeData.getWatchlistSeries().size() == 0 && key.equals("SerieWatchlist")){
            Toast.makeText(context,"Adicione series a Watchlist!",Toast.LENGTH_SHORT).show();
        }
        if (homeData.getWatchedSeries().size() == 0 && key.equals("SerieWatched")){
            Toast.makeText(context,"Adicione series a Watched!",Toast.LENGTH_SHORT).show();
        }
    }
}
