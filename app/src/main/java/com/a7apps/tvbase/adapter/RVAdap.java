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
import com.a7apps.tvbase.ui.dialog.DialogAdd;
import com.a7apps.tvbase.ui.dialog.DialogRemoveWatched;
import com.a7apps.tvbase.ui.dialog.DialogRemoveWatchlist;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class RVAdap extends RecyclerView.Adapter<RVAdap.HolderAdapRV>{
    private Context context;
    private String keyTypeMedia;
    private ArrayList<String> posterArray;
    private ArrayList<String> idArray;
    private FragmentManager manager;
    private boolean isWatchlistMovies;
    private boolean isWatchedMovies;
    private boolean isWatchlistSeries;
    private boolean isWatchedSeries;

    public RVAdap(Context context, ArrayList<String> posterArray, ArrayList<String> idArray, FragmentManager manager, String keyTypeMedia) {
        this.context = context;
        this.keyTypeMedia = keyTypeMedia;
        this.posterArray = posterArray;
        this.idArray = idArray;
        this.manager = manager;
    }

    public static class HolderAdapRV extends RecyclerView.ViewHolder{
        ImageView imageView, ivItemWatched, ivItemWatchlist;
        Button button;
        public HolderAdapRV(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRV);
            button = itemView.findViewById(R.id.btnItemRV);
            ivItemWatched = itemView.findViewById(R.id.ivItemWatched);
            ivItemWatchlist = itemView.findViewById(R.id.ivItemWatchlist);
        }
    }

    @NonNull
    @Override
    public HolderAdapRV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new HolderAdapRV(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapRV holder, int position) {
        Glide.with(context).load(Constants.getIMAGE_URL()+ posterArray.get(position)).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        modUi(holder.ivItemWatchlist, holder.ivItemWatched, position);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,idArray.get(position),Toast.LENGTH_SHORT).show(); //teste pra ver se id está correto
                //Aqui criar o teste pra saber qual dialog chamar ou de ADD ou de REMOVE:
                isAdded(position);
                if (isWatchedMovies || isWatchedSeries){
                    DialogRemoveWatched dialogRemove = new DialogRemoveWatched(context);
                    dialogRemove.show(manager, "CustomDialog1");

                }else if (isWatchlistMovies || isWatchlistSeries){
                    DialogRemoveWatchlist dRemove = new DialogRemoveWatchlist(context);
                    dRemove.show(manager, "CustomDialog2");

                }else {
                    DialogAdd dialogAdd = new DialogAdd(idArray.get(position), context, keyTypeMedia,
                            position, holder.ivItemWatchlist, holder.ivItemWatched, idArray);

                    dialogAdd.show(manager, "CustomDialog");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return posterArray.size();
    }

    //Aqui por enquanto, só torna visivel ivItemWatchlist, fazer
    //tornar uma ou outra view.
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
