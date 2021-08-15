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
import com.a7apps.tvbase.adapter.AdapRV;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.HomeData;
import com.a7apps.tvbase.model.InsertData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FragmentDialog extends DialogFragment {
    private Button btnAddWatchlist, btnAddWatched, btnAddCustomList;
    private String strImage;
    private Context context;
    private String keyTypeMedia;
    private InsertData insertData;
    private int position;
    private ImageView imageView;
    private ArrayList<String> dataArray;
    public FragmentDialog(String strImage, Context context, String keyTypeMedia, int position, ImageView imageView, ArrayList<String> dataArray) {
        this.strImage = strImage;
        this.context = context;
        this.keyTypeMedia = keyTypeMedia;
        this.position = position;
        this.imageView = imageView;
        this.dataArray = dataArray;
        insertData = new InsertData(strImage,context);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        btnAddWatchlist = view.findViewById(R.id.btnAddWatchlist);
        btnAddWatched = view.findViewById(R.id.btnAddWatched);
        btnAddCustomList = view.findViewById(R.id.btnAddCustomList);

        btnAddWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (keyTypeMedia){
                    case Constants.TYPE_MOVIES: insertData.addWatchlistMovies(); break;
                    case Constants.TYPE_SERIES: insertData.addWatchlistSeries(); break;
                }
                modUi();
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
                dismiss();
            }
        });


        return view;
    }

    public void modUi(){
        HomeData homeData = new HomeData(context);
        if (homeData.getWatchlistMovie().contains(dataArray.get(position))){
            imageView.setVisibility(View.VISIBLE);
        }
    }
}
