package com.a7apps.tvbase.ui.movies.tabs;

import android.os.Bundle;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.RVAdap;
import com.a7apps.tvbase.data.DataWatch;

public class WatchedMoviesFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DataWatch dataWatch;
    private RVAdap rvAdap;
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
        progressBar = view.findViewById(R.id.pbWatchedMovies);
        recyclerView = view.findViewById(R.id.rvWatchedMovies);

        return view;
    }
}