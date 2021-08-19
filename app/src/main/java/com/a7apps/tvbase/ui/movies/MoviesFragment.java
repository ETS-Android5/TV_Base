package com.a7apps.tvbase.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.TabAdapMovies;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MoviesFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private TabAdapMovies adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        tabLayout = view.findViewById(R.id.tabLayoutMovies);
        viewPager2 = view.findViewById(R.id.viewPagerMovies);


        new Thread(new Runnable() {
            @Override
            public void run() {
                adapter = new TabAdapMovies(getActivity());
                adapter.notifyDataSetChanged();
                viewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager2.setAdapter(adapter);
                        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
                            @Override
                            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                                switch (position){
                                    case 0: {
                                        tab.setText(getString(R.string.trending));
                                        break;
                                    }
                                    case 1: {
                                        tab.setText(getString(R.string.watchlist));
                                        break;
                                    }
                                    case 2: {
                                        tab.setText(getString(R.string.watched));
                                        break;
                                    }
                                }
                            }
                        }
                        );
                        tabLayoutMediator.attach();
                    }
                });
            }
        }).start();

        return view;
    }
}