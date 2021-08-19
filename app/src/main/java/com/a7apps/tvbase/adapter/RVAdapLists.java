package com.a7apps.tvbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.HomeData;
import com.a7apps.tvbase.ui.dialog.DialogRemoveWatched;
import com.a7apps.tvbase.ui.dialog.DialogRemoveWatchlist;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RVAdapLists extends RecyclerView.Adapter<RVAdapLists.HoldAdapLists> {
    private Context context;
    private String keyTypeMedia;
    private ArrayList<String> posterArray;
    private ArrayList<String> idArray;
    private FragmentManager manager;
    private boolean isWatchlistMovies;
    private boolean isWatchedMovies;
    private boolean isWatchlistSeries;
    private boolean isWatchedSeries;

    public RVAdapLists(Context context, FragmentManager manager, String keyTypeMedia, ArrayList<String> posterArray, ArrayList<String> idArray) {
        this.context = context;
        this.manager = manager;
        this.keyTypeMedia = keyTypeMedia;
        this.posterArray = posterArray;
        this.idArray = idArray;

    }

    public static class HoldAdapLists extends RecyclerView.ViewHolder{
        ImageView imageView, ivItemWatched, ivItemWatchlist;
        Button button;
        public HoldAdapLists(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRVL);
            ivItemWatched = itemView.findViewById(R.id.ivItemWatchedL);
            ivItemWatchlist = itemView.findViewById(R.id.ivItemWatchlistL);
            button = itemView.findViewById(R.id.btnItemRVL);
        }
    }

    @NonNull
    @NotNull
    @Override
    public RVAdapLists.HoldAdapLists onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rvadaplists,parent,false);
        return new HoldAdapLists(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RVAdapLists.HoldAdapLists holder, int position) {
        Glide.with(context).load(Constants.getIMAGE_URL()+posterArray.get(position)).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        modUi(holder.ivItemWatchlist,holder.ivItemWatched,position);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAdded(position);
                if (isWatchedMovies || isWatchedSeries){
                    DialogRemoveWatched dialogRemove = new DialogRemoveWatched(context);
                    dialogRemove.show(manager, "CustomDialog1");

                }else if (isWatchlistMovies || isWatchlistSeries){
                    DialogRemoveWatchlist dRemove = new DialogRemoveWatchlist(context);
                    dRemove.show(manager, "CustomDialog2");

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return posterArray.size();
    }

    public void modUi(ImageView ivWatchlist, ImageView ivWatched, int position){
        isAdded(position);
        if (keyTypeMedia.equals(Constants.TYPE_MOVIES)){
            if (isWatchlistMovies){
                ivWatchlist.setVisibility(View.VISIBLE);
            }
            if (isWatchedMovies){
                ivWatched.setVisibility(View.VISIBLE);
            }
        }

        if (keyTypeMedia.equals(Constants.TYPE_SERIES)){
            if (isWatchlistSeries){
                ivWatchlist.setVisibility(View.VISIBLE);
            }
            if (isWatchedSeries){
                ivWatched.setVisibility(View.VISIBLE);
            }
        }

    }

    public void isAdded(int position){
        HomeData homeData = new HomeData(context);
        isWatchlistMovies = homeData.getWatchlistMovies().contains(idArray.get(position));
        isWatchedMovies = homeData.getWatchedMovies().contains(idArray.get(position));
        isWatchlistSeries = homeData.getWatchlistSeries().contains(idArray.get(position));
        isWatchedSeries = homeData.getWatchedSeries().contains(idArray.get(position));
    }
}
