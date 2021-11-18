package com.a7apps.tvbase.ui.series.tabs;

import android.os.Bundle;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.RVAdapLists;
import com.a7apps.tvbase.assistant.AssistantMethods;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.data.DataWatch;

public class WatchlistSeriesFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DataWatch dataWatch;
    private RVAdapLists adapLists;

    public WatchlistSeriesFrag() {
        // Required empty public constructor
    }

    public static WatchlistSeriesFrag newInstance(String param1, String param2) {
        WatchlistSeriesFrag fragment = new WatchlistSeriesFrag();
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
       View view = inflater.inflate(R.layout.fragment_watchlist_series, container, false);
       progressBar = view.findViewById(R.id.pbWatchlistSeries);
       recyclerView = view.findViewById(R.id.rvWatchlistSeries);
        AssistantMethods methods = new AssistantMethods(getActivity().getApplicationContext());
        methods.checkDb("SerieWatchlist");

       new Thread(new Runnable() {
           @Override
           public void run() {
               dataWatch = new DataWatch(getActivity().getApplicationContext());
               dataWatch.initWatchlistSeries();
               adapLists = new RVAdapLists(getActivity().getApplicationContext(),getParentFragmentManager(),
                       Constants.TYPE_SERIES, dataWatch.getListWatchlistSeries(), dataWatch.getIdWatchlistSeries());
               adapLists.notifyDataSetChanged();
               try {
                   Thread.sleep(800);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       recyclerView.setAdapter(adapLists);
                       progressBar.setVisibility(View.INVISIBLE);
                   }
               });
           }
       }).start();

       return view;
    }
}