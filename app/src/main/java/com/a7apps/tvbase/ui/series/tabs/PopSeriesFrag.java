package com.a7apps.tvbase.ui.series.tabs;

import android.os.Bundle;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.AdapRV;
import com.a7apps.tvbase.data.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopSeriesFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopSeriesFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView recyclerView;
    private AdapRV adapRV;
    private ProgressBar progress;
    private Data data;

    public PopSeriesFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopSeriesFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PopSeriesFrag newInstance(String param1, String param2) {
        PopSeriesFrag fragment = new PopSeriesFrag();
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

        View view = inflater.inflate(R.layout.fragment_pop_series, container, false);
        recyclerView = view.findViewById(R.id.rvPopSeries);
        progress = view.findViewById(R.id.pbFragmentPopSeries);
        recyclerView.setHasFixedSize(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                data = new Data(getActivity().getApplicationContext());
                adapRV = new AdapRV(getActivity().getApplicationContext(), data.getDataPopSeries());
                adapRV.notifyDataSetChanged();
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(adapRV);
                        progress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();

        return view;
    }
}