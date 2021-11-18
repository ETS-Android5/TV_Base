package com.a7apps.tvbase.ui.movies.tabs;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.RVAdap;
import com.a7apps.tvbase.adapter.RVAdapLists;
import com.a7apps.tvbase.assistant.AssistantMethods;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.DataWatch;
import com.a7apps.tvbase.data.HomeData;

public class WatchlistMoviesFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtwlistM;
    private DataWatch dataWatch;
    private RVAdapLists adapLists;
    public WatchlistMoviesFrag() {
        // Required empty public constructor
    }

    public static WatchlistMoviesFrag newInstance(String param1, String param2) {
        WatchlistMoviesFrag fragment = new WatchlistMoviesFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_watchlist_movies,container,false);
       progressBar = view.findViewById(R.id.pbWatchlistMovies);
       recyclerView = view.findViewById(R.id.rvWatchlistMovies);
       txtwlistM = view.findViewById(R.id.txtEmptywlistM);
        AssistantMethods methods = new AssistantMethods(getActivity().getApplicationContext());
        methods.checkDb("MovieWatchlist");

            teste();

       return view;
    }

    public void teste(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dataWatch = new DataWatch(getActivity().getApplicationContext());
                dataWatch.initWatchlistMovies();
                adapLists = new RVAdapLists(getActivity().getApplicationContext(),getParentFragmentManager(),
                        Constants.TYPE_MOVIES, dataWatch.getListWatchlistMovies(), dataWatch.getIdWatchlistMovies());
                adapLists.notifyDataSetChanged();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtwlistM.setVisibility(View.INVISIBLE);
                        recyclerView.setAdapter(adapLists);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}