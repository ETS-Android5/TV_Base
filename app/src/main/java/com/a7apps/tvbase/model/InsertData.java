//Criar classe igual essa com métodos de remover. Eliminar este toast quando
//td estiver pronto.
package com.a7apps.tvbase.model;

import android.content.Context;
import android.widget.Toast;
import com.a7apps.tvbase.database.DatabaseHelper;

public class InsertData {
    private Context context;
    //Substituir strImage para strId
    private String strImage;
    private DatabaseHelper databaseHelper;
    public InsertData(String strImage, Context context) {
        this.strImage = strImage;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void addWatchlistMovies(){
        if (strImage != null && !strImage.equals("")){
            boolean isInserted = databaseHelper.insertWatchlistMovies(strImage);
            if (isInserted){
                Toast.makeText(context,"ADD",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addWatchlistSeries(){
        if (strImage != null && !strImage.equals("")){
            boolean isInserted = databaseHelper.insertWatchlistSeries(strImage);
            if (isInserted){
                Toast.makeText(context,"ADD",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addWatchedMovies(){
        if (strImage != null && !strImage.equals("")){
            boolean isInserted = databaseHelper.insertWatchedMovies(strImage);
            if (isInserted){
                Toast.makeText(context,"ADD",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addWatchedSeries(){
        if (strImage != null && !strImage.equals("")){
            boolean isInserted = databaseHelper.insertWatchedSeries(strImage);
            if (isInserted){
                Toast.makeText(context,"ADD",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
