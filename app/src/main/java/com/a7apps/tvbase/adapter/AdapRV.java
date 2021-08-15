package com.a7apps.tvbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.HomeData;
import com.a7apps.tvbase.ui.dialog.FragmentDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class AdapRV extends RecyclerView.Adapter<AdapRV.HolderAdapRV>{
    private Context context;
    private String keyTypeMedia;
    private ArrayList<String> dataArray;
    private FragmentManager manager;

    public AdapRV(Context context, ArrayList<String> dataArray, FragmentManager manager, String keyTypeMedia) {
        this.context = context;
        this.keyTypeMedia = keyTypeMedia;
        this.dataArray = dataArray;
        this.manager = manager;
    }

    public static class HolderAdapRV extends RecyclerView.ViewHolder{
        ImageView imageView, ivItemWatched, ivItemWatchlist;
        Button imageButton;
        public HolderAdapRV(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRV);
            imageButton = itemView.findViewById(R.id.ibItemRV);
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
        modUi(holder.ivItemWatchlist,position);
       // test(holder.ivItemWatchlist, holder.ivItemWatched, position);
        Glide.with(context).load(Constants.getIMAGE_URL()+dataArray.get(position)).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentDialog dialog = new FragmentDialog(dataArray.get(position), context, keyTypeMedia, position, holder.ivItemWatchlist,dataArray);
                dialog.show(manager, "CustomDialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }

    public void modUi(ImageView imageView, int position){
        HomeData homeData = new HomeData(context);
        if (homeData.getWatchlistMovie().contains(dataArray.get(position))){
            imageView.setVisibility(View.VISIBLE);
        }
    }

    public void test(ImageView view, ImageView view2, int position){
        if (position % 2 == 0){
            view.setVisibility(View.VISIBLE);
        }else {
            view2.setVisibility(View.VISIBLE);
        }
    }

}
