package com.a7apps.tvbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.MainActivity;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.assistant.CustomFragmentDialog;
import com.a7apps.tvbase.ui.movies.tabs.PopMoviesFrag;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class AdapRV extends RecyclerView.Adapter<AdapRV.HolderAdapRV>{
    private Context context;
    private ArrayList<String> dataArray;
    private FragmentManager manager;

    public AdapRV(Context context, ArrayList<String> dataArray, FragmentManager manager) {
        this.context = context;
        this.dataArray = dataArray;
        this.manager = manager;
    }

    public static class HolderAdapRV extends RecyclerView.ViewHolder{
        ImageView imageView;
        Button imageButton;
        public HolderAdapRV(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivItemRV);
            imageButton = itemView.findViewById(R.id.ibItemRV);
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
        Glide.with(context).load(Constants.getIMAGE_URL()+dataArray.get(position)).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomFragmentDialog customFragmentDialog = new CustomFragmentDialog();
                customFragmentDialog.show(manager, "CustomDialog");

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }



}
