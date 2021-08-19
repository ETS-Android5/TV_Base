package com.a7apps.tvbase.ui.movies.tabs;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.RVAdapLists;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.DataWatch;
import com.a7apps.tvbase.data.HomeData;

public class WatchedMoviesFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProgressBar progress;
    private TextView txtwedMovies;
    private DataWatch dataWatch;
    private RVAdapLists adapLists;
    public WatchedMoviesFrag() {
        // Required empty public constructor
    }

    public static WatchedMoviesFrag newInstance(String param1, String param2) {
        WatchedMoviesFrag fragment = new WatchedMoviesFrag();
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
        View view =  inflater.inflate(R.layout.fragment_watched_movies, container, false);
        progress = view.findViewById(R.id.pbWatchedMovies);
        recyclerView = view.findViewById(R.id.rvWatchedMovies);
        txtwedMovies = view.findViewById(R.id.txtEmptywedM);
        HomeData homeData = new HomeData(getActivity().getApplicationContext());
        if (homeData.getWatchedMovies().size() == 0){
            progress.setVisibility(View.INVISIBLE);
            txtwedMovies.setVisibility(View.VISIBLE);
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dataWatch = new DataWatch(getActivity().getApplicationContext());
                    dataWatch.initWatchedMovies();
                    adapLists = new RVAdapLists(getActivity().getApplicationContext(),getParentFragmentManager(),Constants.TYPE_MOVIES,
                            dataWatch.getListWatchedMovies(), dataWatch.getIdWatchedMovies());
                    adapLists.notifyDataSetChanged();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setAdapter(adapLists);
                            progress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }).start();
        }

        return view;
    }
}