package com.a7apps.tvbase.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.HomeData;
import com.a7apps.tvbase.model.InsertData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DialogAdd extends DialogFragment {
    private Button btnAddWatchlist, btnAddWatched;
    private String strId;
    private Context context;
    private String keyTypeMedia;
    private InsertData insertData;
    private int position;
    private ImageView ivWatchlist;
    private ImageView ivWatched;
    private ArrayList<String> idArray;
    private boolean isWatchlistMovies;
    private boolean isWatchedMovies;
    private boolean isWatchlistSeries;
    private boolean isWatchedSeries;

    public DialogAdd(String strId, Context context, String keyTypeMedia, int position,
                     ImageView ivWatchlist, ImageView ivWatched, ArrayList<String> idArray) {
        this.strId = strId;
        this.context = context;
        this.keyTypeMedia = keyTypeMedia;
        this.position = position;
        this.ivWatchlist = ivWatchlist;
        this.ivWatched = ivWatched;
        this.idArray = idArray;
        insertData = new InsertData(strId,context);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add, null);
        btnAddWatchlist = view.findViewById(R.id.btnAddWatchlist);
        btnAddWatched = view.findViewById(R.id.btnAddWatched);

        btnAddWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (keyTypeMedia){
                    case Constants.TYPE_MOVIES: insertData.addWatchlistMovies(); break;
                    case Constants.TYPE_SERIES: insertData.addWatchlistSeries(); break;
                }
                modifyUi();
                dismiss();
            }
        });

        btnAddWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (keyTypeMedia){
                    case Constants.TYPE_MOVIES: insertData.addWatchedMovies(); break;
                    case Constants.TYPE_SERIES: insertData.addWatchedSeries(); break;
                }
                modifyUi();
                dismiss();
            }
        });


        return view;
    }
    /*Serve para mudar a ui em tempo real
    Como é um método que vai ser usado em mais de um dialog
    joga-lo para assistant talvez.
    Melhorar o lance da imageView que está sendo recebida no construtor
    talvez de problema lá no Adapter da recyclerview (o teste deve estar
    bem feito para chamar a dialog certa passando os parametros corretos
    para cada dialog)
     */
    //Aqui o filtro é por watched e watchlist
    public void modifyUi(){
        isAdd(position);
        if (isWatchlistMovies || isWatchlistSeries){
            ivWatchlist.setVisibility(View.VISIBLE);
        }
        if (isWatchedMovies || isWatchedSeries){
            ivWatched.setVisibility(View.VISIBLE);
        }
    }

    public void isAdd(int position){
        HomeData homeData = new HomeData(context);
        isWatchlistMovies = homeData.getWatchlistMovies().contains(idArray.get(position));
        isWatchedMovies = homeData.getWatchedMovies().contains(idArray.get(position));
        isWatchlistSeries = homeData.getWatchlistSeries().contains(idArray.get(position));
        isWatchedSeries = homeData.getWatchedSeries().contains(idArray.get(position));
    }
}
