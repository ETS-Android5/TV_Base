package com.a7apps.tvbase.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.a7apps.tvbase.ui.movies.tabs.PopMoviesFrag;
import com.a7apps.tvbase.ui.movies.tabs.WatchedMoviesFrag;
import com.a7apps.tvbase.ui.movies.tabs.WatchlistMoviesFrag;

public class TabAdapMovies extends FragmentStateAdapter {
    private static final int NUM_TABS = 3;

    public TabAdapMovies(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new PopMoviesFrag(); break;
            case 1: fragment = new WatchlistMoviesFrag(); break;
            case 2: fragment = new WatchedMoviesFrag(); break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
